import java.lang.invoke.MethodHandles.lookup
import java.util.logging.FileHandler
import java.util.logging.Level
import java.util.logging.Logger
import java.util.logging.XMLFormatter

fun main() {
    val logger = Logger.getLogger(lookup().lookupClass().name)

    val fileHandler = FileHandler("test.log.xml")
    fileHandler.filter = { it.level == Level.INFO }  // Filter.isLoggable impl

    fileHandler.formatter = XMLFormatter()
    logger.addHandler(fileHandler)

    logger.info("Info log message")
    logger.log(Level.WARNING) { "Hello ${logger.name}" }
    logger.warning("Hello ${logger.name}")
}


/*object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val logger = Logger.getLogger(Main::class.java.name)
        println(Main::class.java.name)
    }
}*/