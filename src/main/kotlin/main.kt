import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val helpString = "Choose mode:\n" +
        "-reverse outputs the rest of arguments in reverse order\n" +
        "-sum adds the rest of argument and outputs the result\n" +
        "-mul multiplies the rest of argument and outputs the result\n" +
        "-author displays author info and date/time\n" +
        "-help displays this message"

const val authorString = "©Vitaly Artemiev 2021"

fun main(args: Array<String>) {
    require(args.size >= 0) {"No arguments provided"}

    when (args[0]) {
        "-reverse" -> {
            for (s in args.drop(1).reversed()) {
                println(s)
            }
        }
        "-sum" -> {
            println("Sum of args: " +
                    "${args.drop(1).mapNotNull { it.toIntOrNull() }.sum()}")
        }
        "-mul" -> {
            println("Product of args: " +
                    "${args.drop(1).mapNotNull { it.toIntOrNull() }.reduce {acc, i -> acc * i}} ")
        }
        "-help" -> {
            println(helpString)
        }
        "-author" -> {
            val dt = LocalDateTime.now()
            println(authorString + "\n" +
                    "Current date: ${dt.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"))}\n" +
                    "Current time: ${dt.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"))}")
        }
        else -> {
            println("Invalid mode.\n" + helpString)
        }
    }
}
