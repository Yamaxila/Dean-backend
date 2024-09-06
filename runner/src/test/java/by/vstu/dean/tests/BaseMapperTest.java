package by.vstu.dean.tests;

import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Javers;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public abstract class BaseMapperTest<M extends DBBaseModel, D extends BaseDTO, T extends BaseMapperInterface<D, M>> {

    @Autowired
    @Setter
    protected T mapper;

    protected final Javers javers;

    protected String[] notEqualsFields;

    protected BaseMapperTest(Javers javers) {
        this.javers = javers;
    }

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

    public boolean areObjectsEqual(Object obj1, Object obj2, String... notEqualsFields) {
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

        Diff diff = this.javers.compare(obj1, obj2);

        AtomicBoolean equals = new AtomicBoolean(true);

        String[] finalNotEqualsFields = notEqualsFields;
        diff.groupByObject().forEach(changesByObject -> {
            List<Change> changes = changesByObject.get();

            if (changes == null || changes.isEmpty()) {
                equals.set(true);
                return;
            }

            changes.stream()
                    .filter(p -> p instanceof ValueChange)
                    .map(m -> (ValueChange) m).forEach(change -> {
                        if (Arrays.stream(finalNotEqualsFields).noneMatch(f -> f.equals(change.getPropertyName()))) {
                            equals.set(false);
                            return;
                        }

                    });
        });

        this.printDiff(obj1, obj2);

        return equals.get();
    }

    public void printDiff(Object obj1, Object obj2) {
        if (obj1 == obj2) {
            return;
        }
        if (obj1 == null || obj2 == null) {
            return;
        }
        if (obj1.getClass() != obj2.getClass()) {
            return;
        }

        Diff diff = javers.compare(obj1, obj2);


        log.info("Diff: \n{}", diff.prettyPrint());

    }

    public abstract M getNewEntity();
    public abstract D getNewDTO();
}
