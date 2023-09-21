package by.vstu.dean.requests;

import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;
import org.modelmapper.TypeToken;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class BaseRequest<B> {

    private static final String AUTH_CLIENT_ID = "DEAN", AUTH_CLIENT_SECRET = "DEAN";


    private String url;

    private HttpHeaders headers;

    public BaseRequest (String url) {
        this.url = url;
        this.headers = new HttpHeaders();
    }

    public String run (B entity) {

        RestTemplate restTemplate = new RestTemplate();


        HttpEntity<B> request =
                new HttpEntity<B>(entity, headers);

        return restTemplate.exchange(this.url, HttpMethod.POST, request, String.class).getBody();
    }

    public BaseRequest<B> addHeader(String key, String value) {
        this.headers.add(key, value);
        return this;
    }

    public BaseRequest<B> setAuthHeaders() {

        byte[] encodedAuth = Base64.encodeBase64(
                (AUTH_CLIENT_ID + ":" + AUTH_CLIENT_SECRET).getBytes(StandardCharsets.US_ASCII) );

        this.headers.add(HttpHeaders.AUTHORIZATION, "Basic " + new String( encodedAuth ));
//        this.headers.add("username", AUTH_CLIENT_ID);
//        this.headers.add("password", AUTH_CLIENT_SECRET);
        return this;
    }

    public BaseRequest<B> setMediaType(MediaType type) {
        this.headers.setContentType(type);
        return this;
    }

}
