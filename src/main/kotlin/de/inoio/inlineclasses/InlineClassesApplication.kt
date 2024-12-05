package de.inoio.inlineclasses

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class InlineClassesApplication

fun main(args: Array<String>) {
    runApplication<InlineClassesApplication>(*args)
}

@JvmInline
value class Age(
    val value: Int,
) {
    init {
        require(value >= 18) { "Age must be at least 18" }
    }
}

interface JavaInteger {
    val value: Integer
}

// https://github.com/spring-projects/spring-data-commons/issues/2868
@JvmInline
value class PersonId(
    override val value: Integer,
) : JavaInteger

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
    private val personRepositry: PersonRepository,
) {
    @DeleteMapping("/persons")
    fun deletePersons() = personRepositry.deleteAll()

    @GetMapping("/persons", produces = ["application/json"])
    fun getPersons(): List<Person> = personRepositry.findAll().toList()

    @PutMapping("/persons", produces = ["application/json"])
    fun putPersons(
        @RequestBody person: Person,
    ): Person = personRepositry.save(person)
}
