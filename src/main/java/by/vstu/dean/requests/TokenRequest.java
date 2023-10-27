package by.vstu.dean.requests;

import by.vstu.dean.adapters.json.LocalDateTimeJsonAdapter;
import by.vstu.dean.auth.models.TokenModel;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiModel;
import org.modelmapper.TypeToken;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

/**
 * Класс для получения токена аутентификации.
 */
@ApiModel(description = "Класс для получения токена аутентификации")
public class TokenRequest {

    private String login, password;
    private String clientId, clientSecret;

    private String authUrl;

    /**
     * Конструктор класса TokenRequest.
     *
     * @param url       URL для аутентификации.
     * @param login     Логин пользователя.
     * @param password  Пароль пользователя.
     * @param clientId     Идентификатор клиента.
     * @param clientSecret Секрет клиента.
     */
    public TokenRequest(String url, String login, String password, String clientId, String clientSecret) {
        this.login = login;
        this.password = password;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authUrl = url;
    }

    /**
     * Конструктор класса TokenRequest без использования клиентских данных.
     *
     * @param url      URL для аутентификации.
     * @param login    Логин пользователя.
     * @param password Пароль пользователя.
     */
    public TokenRequest(String url, String login, String password) {
        this.login = login;
        this.password = password;
        this.authUrl = url;
    }

    /**
     * Получить токен аутентификации.
     *
     * @return Токен аутентификации.
     */
    public String getToken() {

        // Проверяем, есть ли действующий токен и не истек ли его срок действия.
        if (System.getProperty("expires_at") != null && Long.parseLong(System.getProperty("expires_at")) > System.currentTimeMillis()) {
            return System.getProperty("accessToken");
        }

        // Создаем экземпляр BaseRequest для выполнения запроса.
        BaseRequest<String> request = new BaseRequest<String>(authUrl)
                .setMediaType(MediaType.APPLICATION_FORM_URLENCODED);

        // Если предоставлены данные клиента, добавляем их в запрос.
        if (this.clientId != null && this.password != null) {
            request.setAuthData(clientId, clientSecret);
        }

        // Устанавливаем заголовки для аутентификации.
        request.setAuthHeaders();

        // Выполняем POST-запрос для получения токена аутентификации.
        String json = request.run(String.format("username=%s&password=%s", this.login, this.password));

        // Десериализуем полученный JSON-ответ, преобразовывая его в объект TokenModel.
        TokenModel tokenModel = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonAdapter()).create()
                .fromJson(json, new TypeToken<TokenModel>() {
                }.getType());


        // Сохраняем полученный токен и его срок действия в системных свойствах.
        System.setProperty("accessToken", tokenModel.getAccessToken());
        System.setProperty("expires_at", tokenModel.getExpiresIn() + System.currentTimeMillis());

        // Возвращаем полученный токен аутентификации.
        return tokenModel.getAccessToken();
    }
}
