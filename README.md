# Dean-Backend

Copyright VSTU 2023 by Yamaxila

## Бэкенд деканата ВГТУ

Данный проект реализует миграцию и доступ к новым данным деканата.

## Документация

Проект имеет Swagger для доступа к информации об эндпоинтах. Если необходима документация по классам, то необходимо
синхронизировать Maven и выполнить:

```
mvn dokka:dokka
```

~~Очень желательно~~ **Обязательно** писать документацию в соответсвии с JavaDoc.

## Загрузка в репозиторий

```
mvn clean package dokka:javadocJar source:jar deploy:deploy-file -Durl=http://dean-host-url/repo -DrepositoryId=reposirory -Dsources=target/Dean-backend-${project.version}-sources.jar -Djavadoc=target/Dean-backend-${project.version}-javadoc.jar -Dfile=target/Dean-backend-${project.version}.jar.original -DpomFile=pom.xml -DskipTests=true
```

### Подключение к другим проектам

```xml
[user.home/.m2/settings.xml]:

<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">
  <profiles>
    <profile>
      <id>vstu</id>
      <repositories>
        <repository>
          <id>repository</id>
          <name>VSTU</name>
          <url>http://dean-host-url/repo</url>
          <releases>
            <enabled>true</enabled>
            <updatePolicy>never</updatePolicy>
          </releases>
          <snapshots>
            <enabled>false</enabled>
          </snapshots>
        </repository>
      </repositories>
    </profile>
  </profiles>
</settings>


[project.dir/pom.xml]:
<repositories>
  <repository>
    <id>repository</id>
    <url>http://dean-host-url/repo</url>
    <name>VSTU</name>
  </repository>
</repositories>
<!--...-->
<dependencies>
  <dependency>
    <groupId>by.vstu</groupId>
    <artifactId>Dean-backend</artifactId>
    <version>${dean.version}</version>
  </dependency>
</dependencies>

```

## Поддержка

Проект имеет набор базовых классов, от которых необходимо строить всю последующую иерархию:

Новый
деканат [future](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/future/), [services](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/services/)

- [DBBaseModel](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/future/DBBaseModel.java) -
  содержит базовый набор полей для любого класса
- [DBBaseModelRepository](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/future/DBBaseModelRepository.java) -
  базовый репозиторий с необходимыми методами
- [BaseService](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/services/BaseService.java) -
  базовый сервис с готовой реализацией
- [BaseController](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/controllers/common/BaseController.java) -
  базовый контроллер с готовой реализацией

Старый деканат [old](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/old/)

- [OldDBBaseModel](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/old/OldDBBaseModel.java) -
  содержит базовый набор полей для старого деканата
- [OldDBBaseModelRepository](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/old/OldDBBaseModelRepository.java) -
  базовый репозиторий с необходимыми методами
  <br>
  <sub>P.S. старый деканат не может иметь сервисов, т.к. в него запрещено заносить данные</sub>

Миграция [migrate](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/services/migrate/)

- [BaseMigrateService](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/services/migrate/BaseMigrateService.java) -
  базовый сервис миграции
- [IMigrateExecutor](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/services/migrate/IMigrateExecutor.java) -
  позволяет автоматически вызывать сервисы
  из [MainMigrateService](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/services/migrate/MainMigrateService.java)
- [MainMigrateService](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/services/migrate/MainMigrateService.java) -
  главный сервис миграции. Позволяет автоматически выполнять миграцию при запуске

Обновление [update](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/services/updates/)

- [BaseUpdateService](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/services/updates/BaseUpdateService.java) -
  базовый сервис обновления
- [IUpdateExecutor](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/services/updates/IUpdateExecutor.java) -
  позволяет автоматически вызывать сервисы и инициализировать
  из [MainUpdateService](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/services/updates/MainUpdateService.java)
- [MainUpdateService](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/services/updates/MainUpdateService.java) -
  главный сервис обновлений. Позволяет автоматически выполнять обновление при запуске(после миграции)

Мапперы и DTO [dto](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/dto/)

- [BaseMapperInterface](https://github.com/Yamaxila/Dean-backend/blob/master/src/main/java/by/vstu/dean/dto/future/BaseMapperInterface.java) -
  базовый маппер
- [BaseDTO](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/dto/BaseDTO.java) - базовый
  DTO
- [MapperImpl](https://github.com/yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/dto/mapper/impl) - пакет
  содержащий имплиментации мапперов

Rest-репозитории для других сервисов

- [ApiRepositoryBase](https://github.com/Yamaxila/Dean-backend/blob/master/src/main/java/by/vstu/dean/requests/repo/ApiRepositoryBase.java) -
  позволяет подключать объекты, как обычный репозиторий (см. Примеры)

## Примеры

### Пример работы с [BaseRequest](https://github.com/Yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/requests/BaseRequest.java):

```java

import by.vstu.dean.core.adapters.*;
import by.vstu.dean.models.students.GroupModel;
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


```

### Пример работы с [ApiRepositoryBase](https://github.com/Yamaxila/Dean-backend/tree/master/src/main/java/by/vstu/dean/requests/repo/ApiRepositoryBase.java):

Создание базового репозитория проекта:

```java
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.requests.TokenRequest;
import by.vstu.dean.requests.repo.ApiRepositoryBase;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public abstract class DeanApiRepositoryBase<O extends DBBaseModel> extends ApiRepositoryBase<O> {

    protected DeanApiRepositoryBase(String endpoint, Class<O> target) {
        super("http://localhost:18076/api", endpoint, "", new TokenRequest("AUTH-URL", "USERNAME", "PASSWORD", "CLIENT_ID", "CLIENT_SECRET").getToken(), target);
    }

}

```

P.S. Для работы репозиториев необходим клиент с scope "rsql". **БЕЗДУМНОЕ ИСПОЛЬЗОВАНИЕ ДАННОГО КЛИЕНТА ЯВЛЯЕТСЯ УГРОЗОЙ
БЕЗОПАСТНОСТИ СЕРВИСА**

Создание репозитория на примере GroupsRepo:

```java

import by.vstu.dean.models.students.GroupModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class GroupsRepo extends DeanApiRepositoryBase<GroupModel> {
    public GroupsRepo() {
        super("groups/", GroupModel.class);
    }

    @Override
    @Cacheable(value = "groups", key = "#id")
    public GroupModel getSingle(Long id) {
        return super.getSingle(id);
    }
}

```

P.S. Я настоятельно рекомендую использовать здесь аннотацию `@Cacheble` на методе `getSingle(Long)`, т.к. большое
количество запросов сильно скажется на быстродействии

