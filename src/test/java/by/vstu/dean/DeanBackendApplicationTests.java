package by.vstu.dean;

import by.vstu.dean.adapters.LocalDateTimeTypeAdapter;
import by.vstu.dean.auth.models.TokenModel;
import by.vstu.dean.requests.BaseRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;

@SpringBootTest
class DeanBackendApplicationTests {

	@Test
	void contextLoads() {

		String json = new BaseRequest<String>("http://192.168.11.252:8888/token?grant_type=password")
				.setAuthHeaders()
				.setMediaType(MediaType.APPLICATION_FORM_URLENCODED)
				.run("username=admin@gmail.com&password=admin");
		System.out.println(json);
		System.out.println(new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter()).create()
				.fromJson(json, TokenModel.class));

	}

}
