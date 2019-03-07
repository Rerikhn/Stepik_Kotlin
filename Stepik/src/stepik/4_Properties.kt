// 4 Properties
// 4.1
class PropertyExample() {
    var counter = 0
    var propertyWithCounter: Int? = null
        set(value: Int?) {
            field = value
            counter++
        }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 4.2 Lazy property
class LazyProperty(val initializer: () -> Int) {
    var value: Int? = null
    val lazy: Int
        get() {
            if (value == null) value = initializer()
            return value!!
        }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 4.3 Delegates example
class LazyProperty(val initializer: () -> Int) {
    val lazyValue: Int by lazy(initializer)
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 4.4 Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class D {
    var date: MyDate by EffectiveDate()
}

class EffectiveDate<R> : ReadWriteProperty<R, MyDate> {

    var timeInMillis: Long? = null

    override fun getValue(thisRef: R, property: KProperty<*>): MyDate {
        return timeInMillis!!.toDate()
    }

    override fun setValue(thisRef: R, property: KProperty<*>, value: MyDate) {
        timeInMillis = value.toMillis()
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////