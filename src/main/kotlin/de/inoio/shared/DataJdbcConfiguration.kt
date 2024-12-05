package de.inoio.shared

import de.inoio.inlineclasses.JavaInteger
import de.inoio.valueobjects.IntValueObject
import de.inoio.valueobjects.IntegerValueObject
import de.inoio.valueobjects.LongValueObject
import de.inoio.valueobjects.StringValueObject
import de.inoio.valueobjects.UuidValueObject
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration
import java.util.UUID

@Configuration
class DataJdbcConfiguration : AbstractJdbcConfiguration() {
    override fun userConverters(): MutableList<*> =
        mutableListOf(
            StringValueObjectConverter(),
            IntegerValueObjectConverter(),
            IntValueObjectConverter(),
            LongValueObjectConverter(),
            LongInlineConverter(),
            UuidValueObjectConverter(),
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

@WritingConverter
class LongValueObjectConverter : Converter<LongValueObject, Long> {
    override fun convert(source: LongValueObject): Long = source.value
}

@WritingConverter
class LongInlineConverter : Converter<JavaInteger, Integer> {
    override fun convert(source: JavaInteger): Integer = source.value.toInt() as Integer
}

@WritingConverter
class StringValueObjectConverter : Converter<StringValueObject, String> {
    override fun convert(source: StringValueObject): String = source.value
}

@WritingConverter
class UuidValueObjectConverter : Converter<UuidValueObject, UUID> {
    override fun convert(source: UuidValueObject): UUID = source.value
}
