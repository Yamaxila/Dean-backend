package by.vstu.dean.tests;

import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
public abstract class BaseMapperTest<M extends DBBaseModel, D extends BaseDTO, T extends BaseMapperInterface<D, M>> {

    @Autowired
    @Setter
    protected T mapper;

    protected String[] notEqualsFields;

    @Test
    public void testToEntity() {
        D dto = getNewDTO();

        M entity = mapper.toEntity(dto);

        assertEntityEqualsDTO(entity, dto);
    }

    @Test
    public void testToDTO() {
        M entity = getNewEntity();

        D dto = mapper.toDto(entity);

        assertEntityEqualsDTO(entity, dto);
    }

    @Test
    public void testUpdateEntity() {
        M entity = getNewEntity();
        D dto = getNewDTO();

        M updated = mapper.partialUpdate(dto, getNewEntity());

        Assertions.assertFalse(areObjectsEqual(entity, updated));
    }

    @Test
    public void testToDTOAndBack() {
        M entity = getNewEntity();
        D dto = mapper.toDto(entity);
        M result = mapper.toEntity(dto);

        Assertions.assertTrue(areObjectsEqual(entity, result, this.notEqualsFields));
    }

    public void assertEntityEqualsDTO(M entity, D dto) {

        M result = this.mapper.toEntity(dto);
        Assertions.assertTrue(areObjectsEqual(entity, result, this.notEqualsFields));

    }

    public static boolean areObjectsEqual(Object obj1, Object obj2, String... notEqualsFields) {
        if (obj1 == obj2) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        if (obj1.getClass() != obj2.getClass()) {
            return false;
        }
        if(notEqualsFields == null)
            notEqualsFields = new String[]{};
        try {

            Field[] fields = ReflectionUtils.getAllFields(obj1.getClass()).toArray(new Field[0]);
            for (Field field : fields) {
                field.setAccessible(true);
                Object value1 = field.get(obj1);
                Object value2 = field.get(obj2);
                if (!Objects.equals(value1, value2)) {
                    if(value1 instanceof DBBaseModel && value2 instanceof DBBaseModel)
                        return BaseMapperTest.areObjectsEqual(value1, value2, notEqualsFields);
                    else {
                        boolean equal = Arrays.stream(notEqualsFields).anyMatch(p -> p.equals(field.getName()));

                        if(!equal)
                            log.warn("Field {} in {} not equal for object {}", field.getName(), obj1.getClass(), obj2.getClass());

                        return equal;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public abstract M getNewEntity();
    public abstract D getNewDTO();
}
