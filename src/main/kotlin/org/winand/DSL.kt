package org.winand

/*
With DslMarker annotation you cannot call methods of outer receivers implicitly:
    validation {
        field("email") {
            field("nested") {}
        }
    }
But you can call it explicitly:
    this@validation.field("nested") {}
 */
@DslMarker annotation class ValidationDsl

data class Rule(val error: String, val check: (String) -> Boolean)

@ValidationDsl
class FieldBuilder(val name: String) {
    private val _rules = mutableListOf<Rule>()
    val rules: List<Rule> get() = _rules.toList()

    fun mustNotBeEmpty() = _rules.add(
        Rule("$name must not be empty", String::isNotEmpty)
    )

    fun mustBeGreaterThan(other: Int) = _rules.add(
        Rule("$name must be > $other") { input ->
            input.toIntOrNull()?.let { it > other } ?: false
        }
    )

    fun mustContain(char: Char) = _rules.add(
        Rule("$name must contain character $char") {
            char in it
        }
    )

    fun mustContain(substr: String) = _rules.add(
        Rule("$name must contain $substr") {
            substr in it
        }
    )

    fun must(msg: String, check: (String) -> Boolean) = _rules.add(
        Rule("$name must $msg", check)
    )
}

@ValidationDsl
class ValidationBuilder {
    private val _fields = mutableListOf<FieldBuilder>()

    fun field(name: String, init: FieldBuilder.() -> Unit) {
        _fields += FieldBuilder(name).apply(init)
    }

    fun validate(input: Map<String, String>): List<String> {
        val errors = mutableListOf<String>()
        _fields.forEach { field ->
            field.rules.forEach { rule ->
                if (!rule.check(input[field.name] ?: ""))
                    errors += rule.error //.format(field.name)
            }
        }
        return errors
    }
}

fun validation(block: ValidationBuilder.() -> Unit) =
    ValidationBuilder().apply(block)

fun main() {
    val validation = validation {
        field("email") {
            mustNotBeEmpty()
            mustContain('@')
        }
        field("age") {
            mustBeGreaterThan(18)
        }
        field("name") {
            must("be Steve") { it == "Steve"}
        }
    }

    val data = mapOf(
        "email" to "hello@there.org",
        "age" to "17",
        "name" to "John",
    )

    val result =  validation.validate(data)
    if (result.isEmpty())
        println("Validation passed!")
    else {
        println("Validation failed:")
        println(result)
    }
}