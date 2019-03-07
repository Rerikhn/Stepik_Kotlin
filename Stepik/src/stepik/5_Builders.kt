// 5 Builders
// 5.1 Extension function literals
fun task(): List<Boolean> {
    val isEven: Int.() -> Boolean = { this % 2 == 0 }
    val isOdd: Int.() -> Boolean = { this % 2 != 0 }

    return listOf(42.isOdd(), 239.isOdd(), 294823098.isEven())
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 5.2 String and map builders
import java.util.HashMap

fun <K, V> buildMap(build: HashMap<K, V>.() -> Unit): Map<K,V> {
    val map = HashMap<K, V>()
    map.build()
    return map
}

fun usage(): Map<Int, String> {
    return buildMap {
        put(0, "0")
        for (i in 1..10) {
            put(i, "$i")
        }
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 5.3 The 'apply' function
fun <T> T.myApply(f: T.() -> Unit): T { f(); return this }

fun createString(): String {
    return StringBuilder().myApply {
        append("Numbers: ")
        for (i in 1..10) {
            append(i)
        }
    }.toString()
}

fun createMap(): Map<Int, String> {
    return hashMapOf<Int, String>().myApply {
        put(0, "0")
        for (i in 1..10) {
            put(i, "$i")
        }
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 5.4 HTML builder
fun renderProductTable(): String {
    return html {
        table {
            tr(color = getTitleColor()){
                td {
                    text("Product")
                }
                td {
                    text("Price")
                }
                td {
                    text("Popularity")
                }
            }
            val products = getProducts()
            for ((index, product) in products.withIndex()) {
                tr {
                    td (color = getCellColor(index, 0)) {
                        text(product.description)
                    }
                    td (color = getCellColor(index, 1)) {
                        text(product.price)
                    }
                    td (color = getCellColor(index, 2)) {
                        text(product.popularity)
                    }
                }
            }
        }
    }.toString()
}

fun getTitleColor() = "#b9c9fe"
fun getCellColor(index: Int, row: Int) = if ((index + row) %2 == 0) "#dce4ff" else "#eff2ff"
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 5.5 Builders: how it works
//'td' is: function invocation.