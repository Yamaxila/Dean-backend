package by.vstu.dean;

import by.vstu.dean.adapters.LocalDateTimeTypeAdapter;
import by.vstu.dean.adapters.LocalDateTypeAdapter;
import by.vstu.dean.adapters.json.LocalDateJsonAdapter;
import by.vstu.dean.adapters.json.LocalDateTimeJsonAdapter;
import by.vstu.dean.future.models.students.GroupModel;
import by.vstu.dean.requests.BaseRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class BaseRequestTest {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateJsonAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTypeAdapter()).create();

    @Test
    void contextLoad() {
        BaseRequest<String> baseRequest = new BaseRequest<String>("http://localhost:18076/api/groups/")
                .setMethod(HttpMethod.GET)
                .setToken("BEARER TOKEN")
                .setMediaType(MediaType.APPLICATION_FORM_URLENCODED);

        String json = baseRequest.run("");

        gson.fromJson(json, new TypeToken<List<GroupModel>>() {
        }.getType());

        json = baseRequest.setUrl("http://localhost:18076/api/groups/58/").run("");

        gson.fromJson(json, GroupModel.class);

    }

}