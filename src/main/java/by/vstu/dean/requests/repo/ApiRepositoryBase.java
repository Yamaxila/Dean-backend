package by.vstu.dean.requests.repo;

import by.vstu.dean.adapters.LocalDateTimeTypeAdapter;
import by.vstu.dean.adapters.LocalDateTypeAdapter;
import by.vstu.dean.adapters.json.LocalDateJsonAdapter;
import by.vstu.dean.adapters.json.LocalDateTimeJsonAdapter;
import by.vstu.dean.requests.BaseRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoRepositoryBean
public abstract class ApiRepositoryBase<O> {

    protected BaseRequest<String> request;

    protected final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateJsonAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTypeAdapter()).create();

    protected String params;

    protected ApiRepositoryBase(String host, String endpoint, String params, String token) {
        this.request = new BaseRequest<>(host + "/" + endpoint);
        this.request.setMediaType(MediaType.APPLICATION_JSON);
        this.request.setToken(token);
        this.params = params;
    }

    public List<O> getAll() {
        this.request.setMethod(HttpMethod.GET);
        System.out.println(this.request.getUrl());
        String json = this.request.run(this.params);
        return gson.fromJson(json, new TypeToken<List<O>>(){}.getType());
    }
    public O getSingle(Long id) {
        System.out.println(this.request.getUrl());

        this.request.setMethod(HttpMethod.GET);
        this.request.setUrl(this.request.getUrl() + "/" + id.toString());
        String json = this.request.run(this.params);
        return gson.fromJson(json, new TypeToken<O>(){}.getType());
    }

    public List<O> rsql(String rsql) {
        this.request.setMethod(HttpMethod.GET);
        this.request.setUrl(this.request.getUrl() + "/" + rsql);
        String json = this.request.run(this.params);
        return gson.fromJson(json, new TypeToken<O>(){}.getType());
    }
}
