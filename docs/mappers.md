## Мапперы

Каждый маппер является интерфейсом и наследуется от [BaseMapperInterface](https://github.com/Yamaxila/Dean-backend/tree/dev/core/src/main/java/by/vstu/dean/core/models/mapper/BaseMapperInterface.java).

Интерфейс уже имеет набор готовых решений для корректного маппинга простых объектов. Необходимо только объяснить маппинг в Entity.

Каждый интерфейс должен иметь класс, имплементирующий его.
Например [FacultyMapper](https://github.com/Yamaxila/Dean-backend/tree/dev/app/src/main/java/by/vstu/dean/mapper/v1/FacultyMapper.java) и [FacultyMapperImpl](https://github.com/Yamaxila/Dean-backend/tree/dev/app/src/main/java/by/vstu/dean/mapper/v1/impl/FacultyMapperImpl.java).

Большинство полей можно автоматически мапить прямо через DTO и аннотацию [@ReflectionField](https://github.com/Yamaxila/Dean-backend/tree/dev/core/src/main/java/by/vstu/dean/core/anotations/ReflectionField.java).

Пример работы с аннотацией **@ReflectionField** 
```java
    //Для работы необходимо указать название переменной
    //Так же необходим класс, откуда(куда) будут браться(записываться) данные 
    @ReflectionField(value = "shortName", clazz = FacultyModel.class)
    private String shortName2;
    //Указание названия поля не обязательно, если оно совпадает с моделью 
    @ReflectionField(clazz = FacultyModel.class)
    private String shortName;
```

Далее в маппере можно использовать [ReflectionUtils](https://github.com/Yamaxila/Dean-backend/tree/dev/core/src/main/java/by/vstu/dean/core/utils/ReflectionUtils.java).mapObject();

 - Object o1 - модель
 - Object o2 - DTO
 - boolean reverseTarget - позволяет менять конечную цель (DTO(false) или модель(true))
 - boolean partialUpdate - мапить только заполненные поля 

```java
FacultyDTO dto = (FacultyDTO) ReflectionUtils.mapObject(entity, new FacultyDTO(), false, false);
FacultyModel model = (FacultyModel) ReflectionUtils.mapObject(new FacultyModel(), dto, true, false);
```

