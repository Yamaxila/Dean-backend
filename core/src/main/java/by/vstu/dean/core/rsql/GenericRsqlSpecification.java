package by.vstu.dean.core.rsql;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static by.vstu.dean.core.rsql.RsqlSearchOperation.*;

@AllArgsConstructor
@Setter
@Getter
public class GenericRsqlSpecification<T> implements Specification<T> {

    private String property;
    private ComparisonOperator operator;
    private List<String> arguments;

    @Override
    public Predicate toPredicate(@NotNull Root<T> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder builder) {
        Path<String> propertyExpression = parseProperty(root);
        List<Object> args = castArguments(propertyExpression);
        Object argument = args.get(0);

        switch (Objects.requireNonNull(getSimpleOperator(operator))) {
            case EQUAL:
                if (argument instanceof String)
                    return builder.like(builder.lower(propertyExpression),
                            argument.toString().replace('*', '%').toLowerCase());
                else if (argument == null)
                    return builder.isNull(propertyExpression);
                else return builder.equal(propertyExpression, argument);
            case NOT_EQUAL:
                if (argument instanceof String)
                    return builder.notLike(propertyExpression,
                            argument.toString().replace('*', '%'));
                else if (argument == null)
                    return builder.isNotNull(propertyExpression);
                else return builder.notEqual(propertyExpression, argument);

            case GREATER_THAN:
                return builder.greaterThan(propertyExpression,
                        argument.toString());

            case GREATER_THAN_OR_EQUAL:
                return builder.greaterThanOrEqualTo(propertyExpression,
                        argument.toString());

            case LESS_THAN:
                return builder.lessThan(propertyExpression,
                        argument.toString());

            case LESS_THAN_OR_EQUAL:
                return builder.lessThanOrEqualTo(propertyExpression,
                        argument.toString());
            case IN:
                return propertyExpression.in(args);
            case NOT_IN:
                return builder.not(propertyExpression.in(args));
        }

        return null;
    }

    // This method will help us diving deep into nested property using the dot convention
    // The originial tutorial did not have this, so it can only parse the shallow properties.
    private Path<String> parseProperty(Root<T> root) {
        Path<String> path;
        if (property.contains(".")) {
            // Nested properties
            String[] pathSteps = property.split("\\.");
            String step = pathSteps[0];
            path = root.get(step);

            for (int i = 1; i <= pathSteps.length - 1; i++) {
                path = path.get(pathSteps[i]);
            }
        } else {
            path = root.get(property);
        }
        return path;
    }

    private List<Object> castArguments(Path<?> propertyExpression) {
        Class<?> type = propertyExpression.getJavaType();

        return arguments.stream().map(arg -> {
            if (type.equals(Integer.class)) return Integer.parseInt(arg);
            else if (type.equals(Long.class)) return Long.parseLong(arg);
            else if (type.equals(Byte.class)) return Byte.parseByte(arg);
            //FIXME: Add support for enums
//            else if (type.isEnum()) {
//                if (type.equals(EFrame.class))
//                    return StringUtils.canBeInt(arg) ? EFrame.valueOf(Integer.parseInt(arg)) : EFrame.valueOf(arg);
//                else if (type.equals(ELessonType.class))
//                    return StringUtils.canBeInt(arg) ? ELessonType.valueOf(Integer.parseInt(arg)) : ELessonType.valueOf(arg);
//                else if (type.equals(EClassroomType.class))
//                    return StringUtils.canBeInt(arg) ? EClassroomType.valueOf(Integer.parseInt(arg)) : EClassroomType.valueOf(arg);
//                else return Enum.valueOf((Class<? extends Enum>) type, arg);
//            }
            else return arg;
        }).collect(Collectors.toList());
    }
}
