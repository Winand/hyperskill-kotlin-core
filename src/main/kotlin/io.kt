package org.example

import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.useLines


fun main() {
    /*val fileName = "src/reading.txt"
    val lines = File(fileName).readText()
    val textFile = Path("/path/to/textFile.txt")
    textFile.writeText("This is some text.") // (over)write text to the file
    textFile.appendText("This is some text.")

    val fileName22 = "src/reading.txt"
    val file = File(fileName22)
    if (file.exists()) { // checks if file exists
        val text = File(fileName).readText()
        val lines = File(fileName).readLines()
        val bytes = File(fileName).readBytes()
        print(lines)
    } else print("No such file")*/


    val textFile2 = Path("resources/io/words_sequence.txt")
    val l = textFile2.useLines { lines -> lines.maxOfOrNull { it.length} }
    println(l)

    val textFile3 = Path("resources/io/words_with_numbers.txt")
    println(
        textFile3.useLines { lines -> lines.count { it.toIntOrNull() != null } }
    )

    val textFile4 = File("resources/io/text.txt")
    println(
        textFile4.readText().split(' ').size
    )
    textFile4.forEachLine { it }

    val f = File("heeloo")
    f.resolve(File("resources/io/words_sequence.txt"))
}