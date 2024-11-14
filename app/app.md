# Module app

Модуль хранит то, что и называется "Деканатом".

# Package by.vstu.dean.configs

В данном пакете хранятся уникальные, для самого деканата, классы конфигурации.

# Package by.vstu.dean.controllers

В данном пакете хранятся все контроллеры деканата.

## Все контроллеры в by.vstu.dean.controllers разделены на следующие пакеты

- by.vstu.dean.controller.authorized
  **- хранит все контроллеры, которым необходима авторизация (secured)**
- by.vstu.dean.controller.nonauthorized
  **- хранит все контроллеры, которым не нужна авторизация (public)**
- by.vstu.dean.controller.enums
  **- хранит все контроллеры, перечислений (enums)**
- by.vstu.dean.controller.repo
  **- хранит все контроллеры, репозиториев (repo)**
- by.vstu.dean.controller.ws
  **- хранит все контроллеры для работы через WebSocket (ws)**
- by.vstu.dean.controller.files
  **- хранит все контроллеры для работы с файлами (files)**

### Далее контроллеры разделяются по версиям DTO

# Package by.vstu.dean.mapper

В данном пакете хранятся все мапперы.
Маппер представляет собой интерфейс и его реализацию.

# Package by.vstu.dean.repo

В данном пакете хранятся все репозитории для моделей деканата.

# Package by.vstu.dean.services

В данном пакете хранятся все сервисы.



