package stepik
import java.util.*

// 1 Introduction
// 1.1 Hello World
fun start(): String = "OK"
fun main(args: Array<String>) {
    // main function, like in Java
    print(start())
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 1.2 Java to Kotlin auto convert (just copy this code to intellij idea in .kt file)
/*
import java.util.Collection;
import java.util.Iterator;

public class JavaCode {
    public String stepik.toJSON(Collection<Integer> collection) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<Integer> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            sb.append(element);
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
*/
fun toJSON(collection: Collection<Int>): String {
    val sb = StringBuilder()
    sb.append("[")
    val iterator = collection.iterator()
    while (iterator.hasNext()) {
        val element = iterator.next()
        sb.append(element)
        if (iterator.hasNext()) {
            sb.append(", ")
        }
    }
    sb.append("]")
    return sb.toString()
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 1.3 Named arguments (JSON format)
fun joinOptions(options: Collection<String>) = options.joinToString(separator =", ", prefix = "[", postfix = "]")
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 1.4 Default arguments
fun foo(name: String, number: Int = 42, toUpperCase: Boolean = false) =
        (if (toUpperCase) name.toUpperCase() else name) + number

fun useFoo() = listOf(
        foo("a"),
        foo("b", number = 1),
        foo("c", toUpperCase = true),
        foo(name = "d", number = 2, toUpperCase = true)
)
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 1.5 Lambdas
fun containsEven(collection: Collection<Int>): Boolean = collection.any { it % 2 == 0 }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 1.6 Strings (regular expressions) useful for checking reg exceptions: https://regex101.com/
val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

fun getPattern(): String = """\d{2}\s$month\s\d{4}"""
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 1.7 Data classes
data class Person(val name: String, val age: Int)

fun getPeople(): List<Person> {
    return listOf(Person("Alice", 29), Person("Bob", 31))
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 1.8 Nullable types and null safety
fun sendMessageToClient(client: Client?, message: String?, mailer: Mailer) {
    if (client?.personalInfo?.email != null && message != null)
        mailer.sendMessage(client.personalInfo.email, message)
}

class Client (val personalInfo: PersonalInfo?)
class PersonalInfo (val email: String?)
interface Mailer {
    fun sendMessage(email: String, message: String)
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 1.9 (Smart Casts) Type's checking
fun eval(expr: Expr): Int =
        when (expr) {
            is Num -> expr.value
            is Sum -> eval(expr.left) + eval(expr.right)
            else -> throw IllegalArgumentException("Unknown expression")
        }

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 1.10 Extension functions
fun Int.r(): RationalNumber = RationalNumber(this, 1)
fun Pair<Int, Int>.r(): RationalNumber = RationalNumber(this.first, this.second)

data class RationalNumber(val numerator: Int, val denominator: Int)
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 1.11 Object expressions
// Right method
fun getList1(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)
    Collections.sort(arrayList, object : Comparator<Int> {
        override fun compare(x: Int, y: Int): Int = y.compareTo(x)
    })
    return arrayList
}
// More easily
fun getList2(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)
    Collections.sort(arrayList, Comparator<Int>(Int::compareTo).reversed())
    return arrayList
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 1.12 SAM conversion (Call Java from Kotlin)
fun getList3(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)
    Collections.sort(arrayList, { x, y -> y.compareTo(x) })
    return arrayList
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 1.13 Extension functions on collections
fun getList(): List<Int> {
    return arrayListOf(1, 5, 2).sortedDescending()
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



