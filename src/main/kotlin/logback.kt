import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Example {
    private val logger1: Logger = LoggerFactory.getLogger(Example::class.java)
    private val logger2: Logger = LoggerFactory.getLogger("com.example.Example")

    init {
        logger1.info("Info from Logger 1")
        logger2.warn("Warn from Logger 2")  // excluded in logback.xml
        logger1.info("Are loggers the same? {}", logger1 == logger2)

        val result = "Any operation"
        logger1.info("To track the value of a variable. The result is {}", result)

        val divisor = 0
        try {
            val res = 1 / divisor
        } catch (e: ArithmeticException) {
            logger2.error("Something went wrong with divisor {}", divisor, e)
        }

//        logger1.fin
    }
}

fun main() {
    Example()
}
