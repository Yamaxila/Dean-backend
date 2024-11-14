package by.vstu.migrate.v1.service.migrate;

import by.vstu.dean.models.hostels.HostelRoomModel;
import by.vstu.dean.services.HostelRoomService;
import by.vstu.migrate.v1.models.hostels.V1HostelRoomModel;
import by.vstu.migrate.v1.repo.V1HostelRoomModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HostelRoomMigrateService extends BaseMigrateService<HostelRoomModel, V1HostelRoomModel> {

    private final HostelRoomService hostelRoomService;
    private final V1HostelRoomModelRepository v1HostelRoomModelRepository;

    @Override
    public List<HostelRoomModel> convertNotExistsFromDB() {
        return this.convertList(this.v1HostelRoomModelRepository.findAll());
    }

    @Override
    public HostelRoomModel convertSingle(V1HostelRoomModel v1HostelRoomModel, boolean update) {
        HostelRoomModel hrm = new HostelRoomModel();
        hrm.setId(v1HostelRoomModel.getId());
        hrm.setSourceId(v1HostelRoomModel.getSourceId());
        hrm.setStatus(v1HostelRoomModel.getStatus().map());
        hrm.setCreated(v1HostelRoomModel.getCreated());
        hrm.setUpdated(v1HostelRoomModel.getUpdated());

        hrm.setRoomNumber(v1HostelRoomModel.getRoomNumber());
        hrm.setRoomType(v1HostelRoomModel.getRoomType().map());
        hrm.setFloor(v1HostelRoomModel.getFloor());
        hrm.setHostel(v1HostelRoomModel.getHostel().map());

        return hrm;
    }

    @Override
    public HostelRoomModel insertSingle(HostelRoomModel t) {
        return this.hostelRoomService.save(t);
    }

    @Override
    public List<HostelRoomModel> insertAll(List<HostelRoomModel> t) {
        return this.hostelRoomService.saveAll(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
