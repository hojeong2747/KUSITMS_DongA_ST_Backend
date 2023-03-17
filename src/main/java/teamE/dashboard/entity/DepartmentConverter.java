package teamE.dashboard.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter
public class DepartmentConverter implements AttributeConverter<Disease, String> {

    @Override
    public String convertToDatabaseColumn(Disease attribute) {
        if (Objects.isNull(attribute)) {
            return null;
        }
        return attribute.toString();
    }

    @Override
    public Disease convertToEntityAttribute(String dbData) {
        return Disease.기타;
    }
}