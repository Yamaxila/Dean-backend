package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.dto.v1.lessons.ExamTypeDTO;
import by.vstu.dean.dto.mapper.ExamTypeMapper;
import by.vstu.dean.models.lessons.ExamModel;
import by.vstu.dean.repo.ExamModelRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с объектами модели типа экзамена.
 */
@Service
@Cacheable("examtype")
public class ExamTypeService extends BaseService<ExamTypeDTO, ExamModel, ExamTypeMapper, ExamModelRepository> {


    public ExamTypeService(ExamModelRepository repo, ExamTypeMapper mapper) {
        super(repo, mapper);
    }
}
