## Поддержка WebSocket

Деканат уже имеет всю логику работы со своими типами через WebSocket.

Всё общение производится через ссылки с пакетом данных.

### Взаимодействие через WebSocket

#### Пакет WebSocket

Пакет в JSON-формате имеет следующий вид:

```json
{
  "payloadType": "string",
  "payload": "any_object_or_primitive",
  "sessionId": "session"
}
```

- payloadType(TEXT, id)
  тип полезных данных пакета
    - UNKNOWN, 0 - неизвестный тип
    - OBJECT, 1 - объект
    - ARRAY, 2 - массив
    - OBJECT_ARRAY, 3 - массив объектов
    - PRIMITIVE, 4 - примитив (int, long, string, boolean)
    - SESSION, 5 - сессия (присутствует во всех пакетах. указывает, какому клиенту предназначен пакет. может быть `*`,
      если пакет предназначен всем)
- payload
  полезная нагрузка пакета (может быть пустой)
- sessionId
  приходит только от сервера. Позволяет определить какому клиенту отправлены данные.

#### Получение сессии

Для получения id сессии, необходимо подписаться на `/topic/session/` и далее отправить пакет следующего содержания на
`/app/session/get`:

```json
{
  "payloadType": "SESSION"
}
```

Когда ядро обработает запрос, то на `/topic/session/` придет следующий ответ:

```json
{
  "payloadType": "SESSION",
  "payload": null,
  "sessionId": "8f74f95c-c9fe-2f58-837b-51268b6cf2e7"
}
```

Данное действие не обязательно, но крайне рекомендуется, чтобы избежать мусора, когда кто-то ещё получает данные.

Далее можно просто подписываться на топики и получать ответы.

#### Полный процесс инициализации

Данный пример показан с использованием библиотеки `StompJs`.

```js
        // ключ для авторизации
const token = "eyJ0eXAiOi......";
// полученная сессия
let sessionId;
// инициализируем клиент с ключом
const stompClient = new StompJs.Client({
    brokerURL: 'ws://127.0.0.1:8080/ws?token=' + token
});
// при подключении
stompClient.onConnect = (frame) => {
    // если сессии нет (а её не может быть), то запрашиваем её
    if (!sessionId) {
        stompClient.subscribe('/topic/session', (session) => {
            if (!sessionId) {
                // обрабатываем ответ
                let json = JSON.parse(session.body);
                // ответ должен быть сессией
                if (json.payloadType === "SESSION") {
                    sessionId = json.sessionId; // куда-нибудь сохраняем сессию 
                    stompClient.unsubscribe('/topic/session'); // отписываемся, чтобы не получать лишние данные
                    subscribeAfterGotSession(); // далее подписываемся на остальные топики
                }
            }
        });
        // после подписки, отправляем запрос
        stompClient.publish({
            destination: "/app/session/get",
            body: JSON.stringify({
                payloadType: "SESSION"
            })
        });
    }
};
```

### Обработка ответов

Данный пример показан с использованием библиотеки `StompJs`.
Все обновления получаются на `/topic/{name}/updates`

```js
// инициализация...
// подписываемся на топик
stompClient.subscribe('/topic/groups/updates', (greeting) => {
    let json = JSON.parse(greeting.body); // обрабатываем Json до объекта
    // проверяем, что сообщение для нашей сессии или предназначено всем
    if (json.sessionId === sessionId || json.sessionId === "*") {
        // смотрим тип приходящих данных
        if (json.payloadType === "OBJECT_ARRAY") { // обрабатываем, как массив
            json = json.payload;
            for (let i = 0; i < json.length; i++) {
                showGreeting(JSON.stringify(json[i])); // как-то выводим
            }
        } else {
            showGreeting(JSON.stringify(json.payload)) // обрабатываем как-то иначе
        }
    }
});

```

## Создание в SpringBoot

Контроллеры для WebSocket аналогичны HTTP-контроллерам, но обязательно должны иметь следующую аннотацию:

```java
@WebSocketTopic(value = "/topic/groups/", dtoClass = V1GroupDTO.class, modelClass = GroupModel.class)
```

#### WebSocketTopic

Аннотация позволяет автоматически регистрировать контроллер в менеджере.

- value - топик, к которому привязан контроллер
- dtoClass - класс DTO (по факту, сейчас нигде не используется, но вдруг)
- modelClass - класс наследующий DBBaseModel, который необходим для поиска контроллера в сервисах при сохранении данных.

Вся реализация скрыта в BaseWSController.

#### Пример реализации для групп:

```java

@Slf4j
@Controller
@WebSocketTopic(value = "/topic/groups/", dtoClass = V1GroupDTO.class, modelClass = GroupModel.class)
@MessageMapping("/groups/")
public class V1GroupsWSController extends BaseWSController<V1GroupDTO, GroupModel, V1GroupMapper, GroupModelRepository, GroupService> {
    /**
     * Конструктор
     *
     * @param messagingTemplate - SimpMessagingTemplate
     * @param service           - Сервис (S)
     * @param mapper            - Маппер (M)
     * @param controllerManager - Менеджер контроллеров
     */
    public V1GroupsWSController(SimpMessagingTemplate messagingTemplate, GroupService service, V1GroupMapper mapper, WSControllerManager controllerManager) {
        super(messagingTemplate, service, mapper, controllerManager);
    }
}
```

### Работа без BaseWSController

Для работы без BaseWSController, можно просто имплементировать методы из WSListener.
Тогда необходимо вручную зарегистрировать контроллер в менеджере.
Реализация инициализации есть
в [BaseWSController#initClass()](https://github.com/yamaxila/Dean-backend/tree/dev/core/src/main/java/by/vstu/dean/core/websocket/controllers/BaseWSController.java)

### Работа без какого-либо взаимодействия с деканатом

Для работы без взаимодействия с деканатом, можно создать обычный контроллер, в соответствии с документацией Spring Boot
3.
Пример такого контроллера предствален
в [WSSessionController](https://github.com/yamaxila/Dean-backend/tree/dev/core/src/main/java/by/vstu/dean/core/websocket/controllers/WSSessionController.java)

