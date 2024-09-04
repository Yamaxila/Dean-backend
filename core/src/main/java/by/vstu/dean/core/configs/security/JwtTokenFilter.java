package by.vstu.dean.core.configs.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter implements Filter {

    @Autowired
    private JwtDecoder decoder;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain)
            throws ServletException, IOException {

        String token = resolveToken(request);

        if(token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        Jwt jwt = this.decoder.decode(token);

        if (jwt.getAudience().contains("dean")) {
            filterChain.doFilter(request, response);
        } else
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Error: resourceId is not `dean`");

    }


    private String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer " )) {
            return bearerToken.substring(7);
        }
        return null;
    }


}