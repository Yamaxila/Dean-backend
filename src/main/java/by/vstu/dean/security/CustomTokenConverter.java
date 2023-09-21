package by.vstu.dean.security;

import java.util.Map;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

public class CustomTokenConverter extends DefaultAccessTokenConverter {
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication auth2Authentication = super.extractAuthentication(map);
        auth2Authentication.setDetails(map);
        return auth2Authentication;
    }
}
