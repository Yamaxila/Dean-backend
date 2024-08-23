## Примеры

### Пример работы с [BaseRequest](https://github.com/Yamaxila/Dean-backend/tree/dev/core/src/main/java/by/vstu/dean/core/requests/BaseRequest.java):

```java

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeJsonAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateJsonAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create();

    void run() {
        BaseRequest<String> baseRequest = new BaseRequest<String>("http://localhost:18076/api/v1/groups/")
                .setMethod(HttpMethod.GET)
                .setToken("BEARER TOKEN")
                .setMediaType(MediaType.APPLICATION_FORM_URLENCODED);

        String json = baseRequest.run("");

        gson.fromJson(json, new TypeToken<List<GroupModel>>() {
        }.getType());

        json = baseRequest.setUrl("http://localhost:18076/api/v1/groups/58/").run("");

        gson.fromJson(json, GroupModel.class);

    }
```

### Пример работы с [ApiRepositoryBase](https://github.com/Yamaxila/Dean-backend/tree/dev/core/src/main/java/by/vstu/dean/core/requests/repo/ApiRepositoryBase.java):

Создание базового репозитория проекта:

```java
@Component
public class DeanApiRepositoryBase<O extends DBBaseModel> extends ApiRepositoryBase<O> {

    @Autowired
    private TokenRequest tokenRequest;

    public DeanApiRepositoryBase(String endpoint, Class<O> target) {
        super("http://localhost:18076/api/v1/", endpoint, "", target);
        this.setTokenRequest(this.tokenRequest);
    }
}
```

P.S. Для работы репозиториев необходим клиент с scope "rsql".

Создание репозитория на примере GroupsRepo:

```java
@Repository
public class GroupsRepo extends DeanApiRepositoryBase<GroupModel> {
    public GroupsRepo() {
        super("groups/", GroupModel.class);
    }
}
```

