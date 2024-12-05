@file:Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package de.inoio.valueobjects

import java.util.UUID

abstract class ValueObject<T> {
    abstract val value: T

    override fun toString(): String = value.toString()
}

abstract class StringValueObject : ValueObject<String>()

// spring-data needs Integer instead of Int...
abstract class IntegerValueObject : ValueObject<Integer>()

abstract class IntValueObject : ValueObject<Int>()

abstract class LongValueObject : ValueObject<Long>()

abstract class UuidValueObject : ValueObject<UUID>()

abstract class FloatValueObject : ValueObject<Float>()
