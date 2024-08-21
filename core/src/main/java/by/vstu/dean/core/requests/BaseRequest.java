package by.vstu.dean.core.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * Базовый класс для выполнения HTTP-запросов.
 */
@ApiModel(description = "Базовый класс для выполнения HTTP-запросов")
public class BaseRequest<B> {

    private String AUTH_CLIENT_ID = "DEAN", AUTH_CLIENT_SECRET = "DEAN";

    @Getter
    @ApiModelProperty(value = "URL для выполнения запроса")
    private String url;

    private final HttpHeaders headers;
    private HttpMethod method = HttpMethod.POST;

    /**
     * Конструктор класса BaseRequest.
     *
     * @param url URL для выполнения запроса.
     */
    public BaseRequest(String url) {
        this.url = url;
        this.headers = new HttpHeaders();
    }

    /**
     * Установить данные аутентификации.
     *
     * @param clientId     Идентификатор клиента.
     * @param clientSecret Секрет клиента.
     * @return Объект BaseRequest с установленными данными аутентификации.
     */
    public BaseRequest<B> setAuthData(String clientId, String clientSecret) {
        this.AUTH_CLIENT_ID = clientId;
        this.AUTH_CLIENT_SECRET = clientSecret;
        return this;
    }

    /**
     * Выполнить HTTP-запрос.
     *
     * @param entity Сущность, отправляемая в запросе.
     * @return Результат запроса в виде строки.
     */
    public String run(B entity) {

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<B> request =
                new HttpEntity<>(entity, headers);

        return restTemplate.exchange(this.url, this.method, request, String.class).getBody();
    }

    /**
     * Добавить HTTP-заголовок.
     *
     * @param key   Ключ заголовка.
     * @param value Значение заголовка.
     * @return Объект BaseRequest с добавленным заголовком.
     */
    @SuppressWarnings({"unused"})
    public BaseRequest<B> addHeader(String key, String value) {
        this.headers.add(key, value);
        return this;
    }

    /**
     * Установить URL.
     *
     * @param url Новый URL.
     * @return Объект BaseRequest с установленным URL.
     */
    public BaseRequest<B> setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * Установить аутентификационные заголовки Basic.
     *
     * @return Объект BaseRequest с установленными аутентификационными заголовками Basic.
     */
    public BaseRequest<B> setAuthHeaders() {

        byte[] encodedAuth = Base64.encodeBase64(
                (AUTH_CLIENT_ID + ":" + AUTH_CLIENT_SECRET).getBytes(StandardCharsets.US_ASCII));

        this.headers.set(HttpHeaders.AUTHORIZATION, "Basic " + new String(encodedAuth));
        return this;
    }

    /**
     * Установить токен авторизации.
     *
     * @param token Токен авторизации.
     * @return Объект BaseRequest с установленным токеном авторизации.
     */
    public BaseRequest<B> setToken(String token) {
        this.headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return this;
    }

    /**
     * Установить метод HTTP-запроса.
     *
     * @param method Метод HTTP-запроса.
     * @return Объект BaseRequest с установленным методом запроса.
     */
    public BaseRequest<B> setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    /**
     * Установить тип медиа в заголовке.
     *
     * @param type Тип медиа.
     * @return Объект BaseRequest с установленным типом медиа в заголовке.
     */
    public BaseRequest<B> setMediaType(MediaType type) {
        this.headers.setContentType(type);
        return this;
    }
}
