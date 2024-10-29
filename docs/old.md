## Старый деканат и миграция

Деканат имеет модуль миграции из [старой базы](https://github.com/yamaxila/Dean-backend/tree/dev/old/).

- [OldDBBaseModel](https://github.com/yamaxila/Dean-backend/tree/dev/old/src/main/java/by/vstu/dean/old/OldDBBaseModel.java) -
  содержит базовый набор полей для моделей старого деканата
- [OldDBBaseModelRepository](https://github.com/yamaxila/Dean-backend/tree/dev/old/src/main/java/by/vstu/dean/old/OldDBBaseModelRepository.java) -
  базовый репозиторий с необходимыми методами

### [Миграция](https://github.com/yamaxila/Dean-backend/tree/dev/old/src/main/java/by/vstu/dean/old/services/migrate/)

- [BaseMigrateService](https://github.com/yamaxila/Dean-backend/tree/dev/old/src/main/java/by/vstu/dean/old/services/migrate/BaseMigrateService.java) -
  базовый сервис миграции
- [IMigrateExecutor](https://github.com/yamaxila/Dean-backend/tree/dev/old/src/main/java/by/vstu/dean/old/services/migrate/IMigrateExecutor.java) -
  позволяет автоматически вызывать сервисы
  из [MainMigrateService](https://github.com/yamaxila/Dean-backend/tree/dev/old/src/main/java/by/vstu/dean/old/services/migrate/MainMigrateService.java)
- [MainMigrateService](https://github.com/yamaxila/Dean-backend/tree/dev/old/src/main/java/by/vstu/dean/old/services/migrate/MainMigrateService.java) -
  главный сервис миграции. Позволяет автоматически выполнять миграцию при запуске

### [Обновление](https://github.com/yamaxila/Dean-backend/tree/dev/old/src/main/java/by/vstu/dean/old/services/update/)

- [BaseUpdateService](https://github.com/yamaxila/Dean-backend/tree/dev/old/src/main/java/by/vstu/dean/old/services/update/BaseUpdateService.java) -
  базовый сервис обновления
- [IUpdateExecutor](https://github.com/yamaxila/Dean-backend/tree/dev/old/src/main/java/by/vstu/dean/old/services/update/IUpdateExecutor.java) -
  позволяет автоматически вызывать сервисы и инициализировать
  из [MainUpdateService](https://github.com/yamaxila/Dean-backend/tree/dev/old/src/main/java/by/vstu/dean/old/services/update/MainUpdateService.java)
- [MainUpdateService](https://github.com/yamaxila/Dean-backend/tree/dev/old/src/main/java/by/vstu/dean/old/services/update/MainUpdateService.java) -
  главный сервис обновлений. Позволяет автоматически выполнять обновление при запуске(после миграции)
