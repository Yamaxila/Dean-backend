package by.vstu.dean.repo;

import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.internal.MessageModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageModelRepository extends DBBaseModelRepository<MessageModel> {
    /**
     * Finds all messages which are not marked as inactive.
     *
     * @return a list of active messages.
     */
    List<MessageModel> findAllByInactiveIs(Boolean inactive);
}
