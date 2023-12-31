package by.vstu.dean.requests.repo;

import by.vstu.dean.adapters.LocalDateTimeTypeAdapter;
import by.vstu.dean.adapters.LocalDateTypeAdapter;
import by.vstu.dean.adapters.json.LocalDateJsonAdapter;
import by.vstu.dean.adapters.json.LocalDateTimeJsonAdapter;
import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.requests.BaseRequest;
import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Базовый класс репозитория для API сущностей.
 */
@NoRepositoryBean
@ApiModel(description = "Базовый класс репозитория для API сущностей")
public abstract class ApiRepositoryBase<O extends DBBaseModel> {

    /**
     * Запрос для выполнения HTTP-запросов к API.
     */
    protected BaseRequest<String> request;

    /**
     * Объект Gson для сериализации и десериализации JSON.
     */
    @Getter
    @ApiModelProperty(value = "Объект Gson для сериализации и десериализации JSON")
    protected final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateJsonAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTypeAdapter()).create();

    /**
     * Параметры запроса.
     */
    protected String params;

    /**
     * URL по умолчанию.
     */
    protected String defaultUrl;

    /**
     * Тип для целевого списка объектов.
     */
    private final TypeToken<ArrayList<O>> targetTypeList;

    /**
     * Конструктор класса ApiRepositoryBase.
     *
     * @param host        Хост API.
     * @param endpoint    Конечная точка API.
     * @param params      Параметры запроса.
     * @param token       Токен авторизации.
     * @param targetClass Класс целевой модели.
     */
    protected ApiRepositoryBase(String host, String endpoint, String params, String token, Class<O> targetClass) {
        this.request = new BaseRequest<>(host + "/" + endpoint);
        this.request.setMediaType(MediaType.APPLICATION_JSON);
        this.request.setToken(token);
        this.defaultUrl = this.request.getUrl();
        this.params = params;
        this.targetTypeList = new TypeToken<ArrayList<O>>() {
        }.where(new TypeParameter<O>() {
        }, targetClass);
    }

    /**
     * Получить список всех объектов.
     *
     * @return Список объектов.
     */
    public List<O> getAll() {
        return this.rsql("");
    }

    /**
     * Получить один объект по идентификатору.
     *
     * @param id Идентификатор объекта.
     * @return Объект с указанным идентификатором или null, если объект не найден.
     */
    @SuppressWarnings("unused")
    public O getSingle(Long id) {
        List<O> o = this.rsql("id==" + id);
        if (o == null || o.isEmpty())
            return null;
        return o.get(0);
    }

    /**
     * Выполнить запрос RSQL и получить список объектов.
     *
     * @param rsql RSQL-запрос.
     * @return Список объектов, удовлетворяющих RSQL-запросу.
     */
    public List<O> rsql(String rsql) {
        this.request.setMethod(HttpMethod.GET);
        this.request.setUrl(this.defaultUrl + "rsql?sql=" + rsql);
        String json = this.request.run(this.params);
        return gson.fromJson(json, targetTypeList.getType());
    }

    /**
     * Выполнить сырой RSQL-запрос.
     *
     * @param rsql Сырой RSQL-запрос.
     * @return Результат RSQL-запроса в виде строки JSON.
     */
    @SuppressWarnings("unused")
    public String rawRSQl(String rsql) {
        this.request.setMethod(HttpMethod.GET);
        this.request.setUrl(this.defaultUrl + "rsql?sql=" + rsql);
        return this.request.run(this.params);
    }
}
