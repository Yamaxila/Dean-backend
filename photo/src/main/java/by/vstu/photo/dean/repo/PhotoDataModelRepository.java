package by.vstu.photo.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.photo.dean.models.PhotoDataModel;

import java.util.List;

public interface PhotoDataModelRepository extends OldDBBaseModelRepository<PhotoDataModel> {

    List<PhotoDataModel> findByCardNumber(String caseNumber);

}
