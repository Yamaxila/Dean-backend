# Dean-Backend
Copyright VSTU 2023 by Yamaxila

## Бэкенд деканата ВГТУ

Данный проект реализует миграцию и доступ к новым данным деканата. 

### Документация
Проект имеет Swagger для доступа к информации об эндпоинтах. Если необходима документация по классам, то необходимо синхронизировать Maven и выполнить:
```
mvn dokka:dokka
```
~~Очень желательно~~ **Обязательно** писать документацию в соответсвии с JavaDoc. 

### Поддержка

Проект имеет набор базовых классов, от которых необходимо строить всю последующую иерархию:

Новый деканат [future](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/future/), [services](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/services/)
- [DBBaseModel](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/future/DBBaseModel.java) - содержит базовый набор полей для любого класса
- [DBBaseModelRepository](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/future/DBBaseModelRepository.java) - базовый репозиторий с необходимыми методами
- [BaseService](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/services/BaseService.java) - базовый сервис с готовой реализацией
- [BaseController](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/controllers/common/BaseController.java) - базовый контроллер с готовой реализацией 

Старый деканат [old](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/old/)
- [OldDBBaseModel](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/old/OldDBBaseModel.java) - содержит базовый набор полей для старого деканата
- [OldDBBaseModelRepository](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/old/OldDBBaseModelRepository.java) - базовый репозиторий с необходимыми методами
<br>
<sub>P.S. старый деканат не может иметь сервисов, т.к. в него запрещено заносить данные</sub>

Миграция [migrate](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/services/migrate/)
- [BaseMigrateService](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/services/migrate/BaseMigrateService.java) - базовый сервис миграции
- [IMigrateExecutor](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/services/migrate/IMigrateExecutor.java) - позволяет автоматически вызывать сервисы из [MainMigrateService](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/services/migrate/MainMigrateService.java)
- [MainMigrateService](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/services/migrate/MainMigrateService.java) - главный сервис миграции. Позволяет автоматически выполнять миграцию при запуске

### Примеры

- Пример работы с [BaseRequest](https://github.com/Yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/requests/BaseRequest.java):

```java
import by.vstu.dean.adapters.json.*;
import by.vstu.dean.adapters.*;
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
class BaseRequestExample {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateJsonAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create();

    @Test
    void contextLoad() {
        String json = new BaseRequest<String>("http://localhost:18076/api/groups")
                .setMethod(HttpMethod.GET)
                .setToken("TOKEN")
                .setMediaType(MediaType.APPLICATION_JSON)
                .run("");

        gson.fromJson(json, new TypeToken<List<GroupModel>>() {
        }.getType());
    }
}


```


