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
import lombok.Getter;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoRepositoryBean
public abstract class ApiRepositoryBase<O extends DBBaseModel> {

    protected BaseRequest<String> request;

    @Getter
    protected final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateJsonAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTypeAdapter()).create();

    protected String params;
    protected String defaultUrl;
    private final TypeToken<ArrayList<O>> targetTypeList;
    private final TypeToken<O> targetTypeBase;

    protected ApiRepositoryBase(String host, String endpoint, String params, String token, Class<O> targetClass) {
        this.request = new BaseRequest<>(host + "/" + endpoint);
        this.request.setMediaType(MediaType.APPLICATION_JSON);
        this.request.setToken(token);
        this.defaultUrl = this.request.getUrl();
        this.params = params;
        this.targetTypeList = new TypeToken<ArrayList<O>>(){}.where(new TypeParameter<O>(){}, targetClass);
        this.targetTypeBase = TypeToken.of(targetClass);
    }

    public List<O> getAll() {
        return this.rsql("");
    }

    public O getSingle(Long id) {
        List<O> o = this.rsql("id==" + id);
        if(o == null || o.isEmpty())
            return null;
        return o.get(0);
    }

    public List<O> rsql(String rsql) {
        this.request.setMethod(HttpMethod.GET);
        this.request.setUrl(this.defaultUrl + "rsql?sql=" + rsql);
        String json = this.request.run(this.params);
        return gson.fromJson(json, targetTypeList.getType());
    }

    public String rawRSQl(String rsql) {
        this.request.setMethod(HttpMethod.GET);
        this.request.setUrl(this.defaultUrl + "rsql?sql=" + rsql);
        return this.request.run(this.params);
    }

}
