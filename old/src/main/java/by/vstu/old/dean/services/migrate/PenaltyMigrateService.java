package by.vstu.old.dean.services.migrate;

import by.vstu.dean.models.changes.PenaltyModel;
import by.vstu.old.dean.models.DDeviationModel;

import java.util.List;

public class PenaltyMigrateService extends BaseMigrateService<PenaltyModel, DDeviationModel> {
    @Override
    public Long getLastDBId() {
        return 0L;
    }

    @Override
    public List<PenaltyModel> convertNotExistsFromDB() {
        return List.of();
    }

    @Override
    public PenaltyModel convertSingle(DDeviationModel dDeviationModel, boolean update) {
        return null;
    }

    @Override
    public PenaltyModel insertSingle(PenaltyModel t) {
        return null;
    }

    @Override
    public List<PenaltyModel> insertAll(List<PenaltyModel> t) {
        return List.of();
    }

    @Override
    public void migrate() {

    }
}
