/**
 * Advanced exception handling / Try-with-resources https://hyperskill.org/learn/step/25231
 */
import java.io.Closeable
import java.io.FileReader

fun main() {
    val resource = CustomCloseable()
    resource.use {
        println("I am an example of some code!")
        it.exampleMethod()
    }
    println("End of program!")

    val r = FileReader("test.log.xml")
    r.use {

    }
    println(r.ready())  /* EXCEPTION */
}

class CustomCloseable : Closeable {
    override fun close() {
        println("Close resource")
    }

    fun exampleMethod() {
        println("Example of some method for the CustomCloseable class")
    }
}
