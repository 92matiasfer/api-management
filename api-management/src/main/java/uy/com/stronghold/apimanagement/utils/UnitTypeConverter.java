package uy.com.stronghold.apimanagement.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import uy.com.stronghold.apimanagement.enums.UnitType;

@Converter(autoApply = true)
public class UnitTypeConverter implements AttributeConverter<UnitType, String> {

    @Override
    public String convertToDatabaseColumn(UnitType unitType) {
        if (unitType == null) {
            return null;
        }
        return unitType.name(); // O usa cualquier lógica específica para la conversión
    }

    @Override
    public UnitType convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            return UnitType.valueOf(dbData);
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Invalid value for unit type: " + dbData, ex);
        }
    }
}