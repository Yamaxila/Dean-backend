package by.vstu.dean.requests;

import by.vstu.dean.adapters.json.LocalDateTimeJsonAdapter;
import by.vstu.dean.auth.models.TokenModel;
import com.google.gson.GsonBuilder;
import org.modelmapper.TypeToken;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

public class TokenRequest {

    private String login, password;
    private String clientId, clientSecret;

    private String authUrl;

    public TokenRequest(String url, String login, String password, String clientId, String clientSecret) {
        this.login = login;
        this.password = password;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authUrl = url;
    }

    public TokenRequest(String url, String login, String password) {
        this.login = login;
        this.password = password;
        this.authUrl = url;
    }

    public String getToken() {

        if(System.getProperty("expires_at") != null && Long.parseLong(System.getProperty("expires_at")) > System.currentTimeMillis()) {
            return System.getProperty("accessToken");
        }

        BaseRequest<String> request = new BaseRequest<String>(authUrl)
                .setMediaType(MediaType.APPLICATION_FORM_URLENCODED);

        if(this.clientId != null && this.password != null) {
            request.setAuthData(clientId, clientSecret);
        }

        request.setAuthHeaders();

        String json = request.run(String.format("username=%s&password=%s", this.login, this.password));

        TokenModel tokenModel = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonAdapter()).create()
                .fromJson(json, new TypeToken<TokenModel>(){}.getType());

        System.setProperty("accessToken", tokenModel.getAccessToken());
        System.setProperty("expires_at", System.currentTimeMillis() + tokenModel.getExpiresIn());

        return tokenModel.getAccessToken();
    }

}
