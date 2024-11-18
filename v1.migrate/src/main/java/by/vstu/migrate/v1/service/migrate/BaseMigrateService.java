package by.vstu.migrate.v1.service.migrate;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.migrate.v1.V1DBBaseModel;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMigrateService<S extends DBBaseModel, T extends V1DBBaseModel> implements IMigrateExecutor {

    public abstract List<S> convertNotExistsFromDB();

    public S convertSingle(T t) {
        return this.convertSingle(t, false);
    }

    public abstract S convertSingle(T t, boolean update);

    public List<S> convertList(List<T> t) {
        List<S> out = new ArrayList<>();
        t.forEach(tt -> out.add(this.convertSingle(tt)));
        return out;
    }

    public abstract S insertSingle(S t);

    public abstract List<S> insertAll(List<S> t);
}
