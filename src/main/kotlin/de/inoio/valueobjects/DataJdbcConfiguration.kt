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

interface ValueObjectInterface<T> {
    val value: T

    override fun toString(): String = value.toString()
}

abstract class ValueObject<T> {
    abstract val value: T

    override fun toString(): String = value.toString()
}

// spring-data needs Integer instead of Int...
abstract class IntegerValueObject : ValueObject<Integer>()

@WritingConverter
class IntegerValueObjectConverter : Converter<IntegerValueObject, Integer> {
    override fun convert(source: IntegerValueObject): Integer = source.value
}

abstract class IntValueObject : ValueObject<Int>()

@WritingConverter
class IntValueObjectConverter : Converter<IntValueObject, Int> {
    override fun convert(source: IntValueObject): Int = source.value
}
