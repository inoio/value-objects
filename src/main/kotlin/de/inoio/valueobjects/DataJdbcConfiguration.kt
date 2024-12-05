package de.inoio.valueobjects

import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration

@Configuration
class DataJdbcConfiguration : AbstractJdbcConfiguration() {
    override fun userConverters(): MutableList<*> =
        mutableListOf(
            IntegerValueObjectConverter(),
            IntValueObjectConverter(),
        )
}

@WritingConverter
class IntegerValueObjectConverter : Converter<IntegerValueObject, Integer> {
    override fun convert(source: IntegerValueObject): Integer = source.value
}

@WritingConverter
class IntValueObjectConverter : Converter<IntValueObject, Int> {
    override fun convert(source: IntValueObject): Int = source.value
}
