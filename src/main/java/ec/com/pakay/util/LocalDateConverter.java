package ec.com.pakay.util;

import javax.persistence.AttributeConverter;
import java.sql.Date;
import java.util.Optional;

public class LocalDateConverter implements AttributeConverter<java.time.LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(java.time.LocalDate localDate) {
		return Optional.ofNullable(localDate).map(Date::valueOf).orElse(null);
	}

	@Override
	public java.time.LocalDate convertToEntityAttribute(Date date) {
		return Optional.ofNullable(date).map(Date::toLocalDate).orElse(null);
	}
}