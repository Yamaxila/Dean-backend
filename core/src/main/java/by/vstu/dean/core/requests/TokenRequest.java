package by.vstu.dean.core.requests;

import by.vstu.dean.core.adapters.json.LocalDateTimeJsonAdapter;
import by.vstu.dean.core.models.TokenModel;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Класс для получения токена аутентификации.
 */
@ApiModel(description = "Класс для получения токена аутентификации")
@Component
@Getter
@Slf4j
public class TokenRequest {

    @Value("${dean.api.login}")
    @Setter
    private String login;
    @Value("${dean.api.password}")
    @Setter
    private String password;
    @Value("${dean.api.clientId}")
    @Setter
    private String clientId;
    @Value("${dean.api.clientSecret}")
    @Setter
        private String clientSecret;
    @Value("${auth.url}")
    @Setter
    private String authUrl;

    private TokenModel token;

    /**
     * Получить токен аутентификации.
     *
     * @param force принудительное обновление токена
     *
     * @return Токен аутентификации.
     */
    public TokenModel getToken(boolean force) {

        if(force)
            log.debug("forcing getToken()");

        // Проверяем, есть ли действующий токен и не истек ли его срок действия.
        if (!force && (System.getProperty("accessToken", null) != null && System.getProperty("expires_at", null) != null && Long.parseLong(System.getProperty("expires_at")) > System.currentTimeMillis())) {
            log.info("using stored token");
            TokenModel model = this.toModel(System.getProperty("accessToken"));

            if(this.token == null)
                this.token = model;

            return token;
        }

        // Создаем экземпляр BaseRequest для выполнения запроса.
        BaseRequest<String> request = new BaseRequest<String>(authUrl)
                .setMediaType(MediaType.APPLICATION_FORM_URLENCODED);

        // Если предоставлены данные клиента, добавляем их в запрос.
        if (this.clientId != null && this.clientSecret != null) {
            request.setAuthHeaders(clientId, clientSecret);
        }
        log.info("sending request to auth service");

        // Выполняем POST-запрос для получения токена аутентификации.
        String json = request.run(String.format("username=%s&password=%s", this.login, this.password));

        // Десериализуем полученный JSON-ответ, преобразовывая его в объект TokenModel.
        this.token = this.toModel(json);

        // Сохраняем полученный токен и его срок действия в системных свойствах.
        System.clearProperty("accessToken");
        System.clearProperty("expires_at");
        System.setProperty("accessToken", json);
        System.setProperty("expires_at", String.valueOf((Long.parseLong(token.getExpiresIn())*1000L) + System.currentTimeMillis()));

        log.debug("new token: {}", token.getAccessToken());

        // Возвращаем полученный токен аутентификации.
        return token;
    }

    private TokenModel toModel(String json) {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonAdapter()).create()
                .fromJson(json, new TypeToken<TokenModel>() {
                }.getType());
    }

    private String toStringToken(TokenModel token) {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonAdapter()).create()
                .toJson(token);
    }

    public TokenModel getToken() {
        return this.getToken(false);
    }
}
