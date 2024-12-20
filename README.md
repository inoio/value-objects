
# Value Objects
(Matthias Brandt, matthias@inoio.de)

---
## Motivation 1: DDD
> A **Value Object** is an **immutable** object in software design that represents a specific value with **no identity**. These objects are primarily used in Domain-Driven Design (DDD) to model concepts in a problem domain. They are characterized by their immutability, meaning they cannot be changed once created, and **their focus on encapsulating** a specific value or set of values required for a business application. 

From: https://www.dremio.com/wiki/value-object

---
## Motivation 2: Typisierung

`Map<Int, Int>`<!-- element class="fragment" -->

`Map<PersonId, Age>`<!-- element class="fragment" -->

---
## Motivation 2: Typisierung
```kotlin
fun update(personId: Int, age: Int)
```
---
## Motivation 2: Typisierung
wie war die Signatur noch gleich? 
```kotlin
update(18,42)
```

### Der Compiler so: "Ist mir doch egal" <!-- element class="fragment"-->
---
## ...und das alles ohne Converter-Hölle für DB, JSON, etc...?

---
# Machen ist wie reden, nur krasser
aka. coding time!
1. Type Aliases
2. (data class)
3. value class

---
## ⚠ Meinung: Wann nutzen?

- Einheiten (z.B. Power(watt))
- Ist es ein Identifier? -> Sowohl technische, als auch Domänen-IDs  
	- IP  
	- MAC-Addresse  
	- DeviceId (Domänen-ID)  
	- DB-Primary Key  
	- Register-Nummer
	- ...
---
## ⚠ Meinung: Wann nicht nutzen?
- Beschreibende Felder (addendum, Beschreibungstext, etc.)  
- DateTime, Interval (alles, was schon mehr Beschreibt als Long/Int/String)
- Kurzlebige Objekte (bsp: Long als timestamp kommt rein, wird sofort in OffsetDateTime gemappt)
---
## Conclusion
- Bessere Lesbarkeit
- Mehr Typsicherheit
- value classes (wenn unterstützt, sonst data class)
