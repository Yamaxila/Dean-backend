package by.vstu.dean;

import by.vstu.dean.adapters.json.LocalDateTimeJsonAdapter;
import by.vstu.dean.auth.models.TokenModel;
import by.vstu.dean.requests.BaseRequest;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

@SpringBootTest
class AuthTest {

    @Value("${auth.url}")
    private String authUrl;
    @Value("${auth.clientId}")
    private String clientId;
    @Value("${auth.clientSecret}")
    private String clientSecret;
    @Value("${auth.loginData}")
    private String loginData;

    @Test
    void contextLoads() {

        String json = new BaseRequest<String>(authUrl)
                .setAuthData(clientId, clientSecret)
                .setAuthHeaders()
                .setMediaType(MediaType.APPLICATION_FORM_URLENCODED)
                .run(loginData);
        System.out.println(json);
        System.out.println((TokenModel)new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonAdapter()).create()
                .fromJson(json, new TypeToken<TokenModel>(){}.getType()));

    }

}
