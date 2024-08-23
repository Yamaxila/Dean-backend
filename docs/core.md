## Ядро

Ядро деканата имеет весь необходимый набор для работы с ним из других проектов.

- [DBBaseModel](https://github.com/yamaxila/Dean-backend/tree/dev/core/src/main/java/by/vstu/dean/core/models/DBBaseModel.java) -
  содержит базовый набор полей для любой модели
- [DBBaseModelRepository](https://github.com/yamaxila/Dean-backend/tree/dev/core/src/main/java/by/vstu/dean/core/repo/DBBaseModelRepository.java) -
    базовый репозиторий с необходимыми методами
- [BaseService](https://github.com/yamaxila/Dean-backend/tree/dev/core/src/main/java/by/vstu/dean/core/services/BaseService.java) -
  базовый сервис с готовой реализацией
- [BaseController](https://github.com/yamaxila/Dean-backend/tree/dev/core/src/main/java/by/vstu/dean/core/controllers/BaseController.java) -
  базовый контроллер с готовой реализацией
- [EnumController](https://github.com/yamaxila/Dean-backend/tree/dev/core/src/main/java/by/vstu/dean/core/controllers/EnumController.java) -
  базовый контроллер для перечислений с готовой реализацией
- [BaseDTO](https://github.com/yamaxila/Dean-backend/tree/dev/core/src/main/java/by/vstu/dean/core/dto/BaseDTO.java) - 
  содержит базовый набор полей для любого DTO
- [BaseEnum](https://github.com/yamaxila/Dean-backend/tree/dev/core/src/main/java/by/vstu/dean/core/enums/BaseEnum.java) -
  интерфейс, который наследуют все перечисления деканата  
- [EStatus](https://github.com/yamaxila/Dean-backend/tree/dev/core/src/main/java/by/vstu/dean/core/enums/EStatus.java) -
  перечисление всех возможных статусов модели и DTO деканата  

### [Мапперы](https://github.com/yamaxila/Dean-backend/tree/dev/docs/mappers.md)
### [Репозитории и Запросы](https://github.com/yamaxila/Dean-backend/tree/dev/docs/repo.md)

Так же ядро имеет следующие функции:

- [Работа с сокетами](https://github.com/yamaxila/Dean-backend/tree/dev/core/src/main/java/by/vstu/dean/core/sockets/)
- [Поддержка RSQL](https://github.com/yamaxila/Dean-backend/tree/dev/core/src/main/java/by/vstu/dean/core/rsql/)
- [Адаптеры для Gson](https://github.com/yamaxila/Dean-backend/tree/dev/core/src/main/java/by/vstu/dean/adapters/)
- [Аннотации](https://github.com/yamaxila/Dean-backend/tree/dev/core/src/main/java/by/vstu/dean/anotations/)