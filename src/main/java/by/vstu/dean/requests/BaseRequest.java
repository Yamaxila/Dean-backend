package by.vstu.dean.requests;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

public class BaseRequest<B> {

    private String AUTH_CLIENT_ID = "DEAN", AUTH_CLIENT_SECRET = "DEAN";

    private String url;

    private final HttpHeaders headers;
    private HttpMethod method = HttpMethod.POST;

    public BaseRequest(String url) {
        this.url = url;
        this.headers = new HttpHeaders();
    }

    public BaseRequest<B> setAuthData(String clientId, String clientSecret) {
        this.AUTH_CLIENT_ID = clientId;
        this.AUTH_CLIENT_SECRET = clientSecret;
        return this;
    }

    public String run(B entity) {

        RestTemplate restTemplate = new RestTemplate();


        HttpEntity<B> request =
                new HttpEntity<>(entity, headers);

        return restTemplate.exchange(this.url, this.method, request, String.class).getBody();
    }

    @SuppressWarnings({"unused"})
    public BaseRequest<B> addHeader(String key, String value) {
        this.headers.add(key, value);
        return this;
    }

    public BaseRequest<B> setUrl(String url) {
        this.url = url;
        return this;
    }

    public BaseRequest<B> setAuthHeaders() {

        byte[] encodedAuth = Base64.encodeBase64(
                (AUTH_CLIENT_ID + ":" + AUTH_CLIENT_SECRET).getBytes(StandardCharsets.US_ASCII));

        this.headers.add(HttpHeaders.AUTHORIZATION, "Basic " + new String(encodedAuth));
        return this;
    }

    public BaseRequest<B> setToken(String token) {
        this.headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return this;
    }

    public BaseRequest<B> setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public BaseRequest<B> setMediaType(MediaType type) {
        this.headers.setContentType(type);
        return this;
    }

}
