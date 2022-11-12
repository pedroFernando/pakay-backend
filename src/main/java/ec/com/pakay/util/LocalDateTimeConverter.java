package ec.com.pakay.util;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.util.Optional;

public class LocalDateTimeConverter implements AttributeConverter<java.time.LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(java.time.LocalDateTime localDateTime) {
        return Optional.ofNullable(localDateTime)
                .map(Timestamp::valueOf)
                .orElse(null);
    }

    @Override
    public java.time.LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        return Optional.ofNullable(timestamp)
                .map(Timestamp::toLocalDateTime)
                .orElse(null);
    }
}