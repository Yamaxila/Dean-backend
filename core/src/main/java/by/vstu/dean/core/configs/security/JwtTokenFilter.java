package by.vstu.dean.core.configs.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter implements Filter {


    private static final List<UUID> validatedTokens = new ArrayList<>();

    @Autowired
    private JwtDecoder decoder;

    @Value("${auth.resourceIds}")
    private String allowedResourceIds;

    @Value("${auth.validateUrl}")
    private String validateUrl;

    @Value("${auth.needValidation}")
    private Boolean needValidation;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain)
            throws ServletException, IOException {

        String token = resolveToken(request);

        if(token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            Jwt jwt = this.decoder.decode(token);

            if (jwt == null) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Error: Token parse failed!");
                return;
            }
            if (this.needValidation)
                if (!this.validateToken(jwt.getTokenValue(), jwt.getId())) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Error: Token validation failed!");
                    return;
                }

            if (Arrays.stream(this.allowedResourceIds.split(",")).anyMatch(p -> jwt.getAudience().contains(p) || p.equals("*")) || jwt.getAudience().contains("*")) {
                filterChain.doFilter(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Error: resourceId is not allowed!");
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Error: Token parse failed!");
        }
    }

    private boolean validateToken(String token, String jti) {

        if (validatedTokens.contains(UUID.fromString(jti)))
            return true;

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> request = new HttpEntity<>("", new HttpHeaders());

        try {
            ResponseEntity<String> response = restTemplate.exchange(this.validateUrl + "?token=" + token, HttpMethod.POST, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                log.debug("Adding token to token store!");
                validatedTokens.add(UUID.fromString(jti));
                return true;
            }

            log.debug("{}: {}", response.getStatusCode().value(), response.getBody());

        } catch (Exception e) {
            log.debug("Token validation failed with message {}", e.getMessage());
        }

        return false;

    }

    @Scheduled(cron = "0 0 */6 * * *")
    public void clearTokens() {
        log.info("Clearing token store!");
        validatedTokens.clear();
    }

    public boolean hasAnyResourceIds(String... resourceIds) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        String token = this.resolveToken(attr.getRequest());

        if (token == null)
            return false;

        try {
            Jwt jwt = this.decoder.decode(token);

            if (jwt == null)
                return false;

            return jwt.getAudience().stream().anyMatch(p -> Arrays.asList(resourceIds).contains(p));
        } catch (Exception e) {
            return false;
        }
    }



    private String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer " )) {
            return bearerToken.substring(7);
        }
        //поддержка авторизации WebSocket
        if (req.getParameter("token") != null) {
            return req.getParameter("token");
        }

        return null;
    }


}