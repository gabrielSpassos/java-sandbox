package com.gabrielspassos.poc.config;

import com.gabrielspassos.poc.entity.value.JsonbPayload;
import org.postgresql.util.PGobject;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.mapping.JdbcValue;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.sql.JDBCType;
import java.sql.SQLException;
import java.util.List;

@Configuration
public class JdbcConfiguration extends AbstractJdbcConfiguration {

    @Override
    protected List<?> userConverters() {
        return List.of(
                JsonbPayloadWritingConverter.INSTANCE,
                JsonbPayloadReadingConverter.INSTANCE
        );
    }

    @WritingConverter
    enum JsonbPayloadWritingConverter implements Converter<JsonbPayload, JdbcValue> {
        INSTANCE;

        @Override
        public JdbcValue convert(JsonbPayload source) {
            var jsonObject = new PGobject();
            jsonObject.setType("jsonb");

            try {
                jsonObject.setValue(source.value());
            } catch (SQLException exception) {
                throw new IllegalArgumentException("Invalid JSONB payload", exception);
            }

            return JdbcValue.of(jsonObject, JDBCType.OTHER);
        }
    }

    @ReadingConverter
    enum JsonbPayloadReadingConverter implements Converter<PGobject, JsonbPayload> {
        INSTANCE;

        @Override
        public JsonbPayload convert(PGobject source) {
            return new JsonbPayload(source.getValue());
        }
    }
}
