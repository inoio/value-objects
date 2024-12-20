package de.inoio.valueobjects

import com.fasterxml.jackson.annotation.JsonValue
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class ValueObjectsApplication

fun main(args: Array<String>) {
    runApplication<ValueObjectsApplication>(*args)
}

data class Age(
    @JsonValue override val value: Integer,
) : IntegerValueObject() {
    init {
        require(value >= 18) { "Age must be at least 18" }
    }
}

data class PersonId(
    @JsonValue override val value: Integer,
) : IntegerValueObject()

@Table
data class Person(
    @Id
    val id: PersonId?,
    val name: String,
    val age: Age,
)

@Repository
interface PersonRepository : CrudRepository<Person, PersonId>

@RestController
class PersonController(
    private val personRepository: PersonRepository,
) {
    @GetMapping("/persons", produces = ["application/json"])
    fun getPersons(): List<Person> = personRepository.findAll().toList()

    @PutMapping("/persons", produces = ["application/json"])
    fun putPersons(
        @RequestBody person: Person,
    ): Person {
//        val doesNotCompile = Person(person.age, person.name, person.age)
        return personRepository.save(person)
    }
}
