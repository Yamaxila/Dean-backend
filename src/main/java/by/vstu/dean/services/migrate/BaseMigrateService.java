package by.vstu.dean.services.migrate;


import java.util.List;

public abstract class BaseMigrateService<S, T> implements IMigrateExecutor {

    public abstract Long getLastDBId();

    public abstract List<S> convertNotExistsFromDB();

    public abstract S convertSingle(T t);

    public abstract List<S> convertList(List<T> t);

    public abstract S insertSingle(S t);

    public abstract List<S> insertAll(List<S> t);

}
