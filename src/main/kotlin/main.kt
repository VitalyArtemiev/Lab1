import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val helpString = "Choose mode:\n" +
        "-reverse outputs the rest of arguments in reverse order\n" +
        "-sum adds the rest of argument and outputs the result\n" +
        "-mul multiplies the rest of argument and outputs the result\n" +
        "-author displays author info and date/time\n" +
        "-maxmin displays largest and smallest elements\n" +
        "-primes displays only prime arguments\n" +
        "-halfsum displays elements that are a half-sum of their neighbors\n" +
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
        "-maxmin" -> {
            val list = args.drop(1).mapNotNull { it.toIntOrNull() }
            val max = list.maxOrNull()
            val min = list.minOrNull()

            if (max == null) {
                println("No numbers found")
            } else {
                println("Max element: $max")
                println("Min element: $min")
            }
        }
        "-primes" -> {
            val list = args.drop(1).mapNotNull { it.toIntOrNull() }.filter {
                if (it > 0 ) {
                    var flag = false

                    for (i in 2..it / 2) {
                        if (it % i == 0) {
                            flag = true
                            break
                        }
                    }

                    !flag
                } else {
                    false
                }

            }
            println("Prime numbers: ${list.toString()}")
        }
        "-halfsum" -> {
            println("Elements that are a half-sum of their neighbors:")

            val list = args.drop(1).mapNotNull { it.toIntOrNull() }.windowed(3,1)
                .filter {
                    it[1] == (it[0] + it[2])/2
                }

            for (l in list) {
                println("${l[1]} = (${l[0]} + ${l[2]})/2")
            }
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
