// 3 Collections
// 3.1 Introduction
fun Shop.getSetOfCustomers(): Set<Customer> = this.customers.toSet()
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 3.2 Filter; map
// Return the set of cities the customers are from
fun Shop.getCitiesCustomersAreFrom(): Set<City> = this.customers.map { it.city }.toSet()

// Return a list of the customers who live in the given city
fun Shop.getCustomersFrom(city: City): List<Customer> = this.customers.filter { it.city == city }.toList()
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 3.3 All, Any and other predicates
// Return true if all customers are from the given city
fun Shop.checkAllCustomersAreFrom(city: City): Boolean = this.customers.all { it.city == city }

// Return true if there is at least one customer from the given city
fun Shop.hasCustomerFrom(city: City): Boolean = this.customers.any { it.city == city }

// Return the number of customers from the given city
fun Shop.countCustomersFrom(city: City): Int = this.customers.count {it.city == city}

// Return a customer who lives in the given city, or null if there is none
fun Shop.findAnyCustomerFrom(city: City): Customer? = this.customers.find { it.city == city }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 3.4 FlatMap
// Return all products this customer has ordered
fun Customer.getOrderedProducts(): Set<Product> = this.orders.flatMap { it.products }.toSet()

// Return all products that were ordered by at least one customer
fun Shop.getAllOrderedProducts(): Set<Product> = this.customers.flatMap { it.getOrderedProducts() }.toSet()
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 3.5 Max; min
// Return a customer whose order count is the highest among all customers
fun Shop.getCustomerWithMaximumNumberOfOrders(): Customer? = this.customers.maxBy { it.orders.size }

// Return the most expensive product which has been ordered
fun Customer.getMostExpensiveOrderedProduct(): Product? = this.orders.flatMap { it.products }.maxBy { it.price }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 3.6 Sort
// Return a list of customers, sorted by the ascending number of orders they made
fun Shop.getCustomersSortedByNumberOfOrders(): List<Customer> = this.customers.sortedBy { it.orders.count() }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 3.7 Sum
// Return the sum of prices of all products that a customer has ordered.
// Note: the customer may order the same product for several times.
fun Customer.getTotalOrderPrice(): Double = this.orders.flatMap { it.products }.sumByDouble { it.price }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 3.8 Group By
// Return a map of the customers living in each city
fun Shop.groupCustomersByCity(): Map<City, List<Customer>> = this.customers.groupBy { it.city }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 3.9 Partition
// Return customers who have more undelivered orders than delivered
fun Shop.getCustomersWithMoreUndeliveredOrdersThanDelivered(): Set<Customer> = this.customers
        .filter {
            val (delivered, undelivered) = it.orders.partition { it.isDelivered }
            undelivered.size > delivered.size
        }.toSet()
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 3.10 Fold
// Return the set of products that were ordered by every customer
fun Shop.getSetOfProductsOrderedByEveryCustomer(): Set<Product> =
        customers.fold(
                customers.flatMap { it.orders.flatMap { it.products } }.toSet(), {
            orderedByAll, customer ->
            orderedByAll.intersect(customer.orders.flatMap { it.products }.toSet())
        })
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 3.11 Compound tasks
// Return the most expensive product among all delivered products
// (use the Order.isDelivered flag)
fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    return this.orders.filter { it.isDelivered }.flatMap { it.products }.maxBy { it.price }
}

// Return how many times the given product was ordered.
// Note: a customer may order the same product for several times.
fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    return customers.flatMap { it.getOrderedProductsList() }.count { it == product }
}

fun Customer.getOrderedProductsList(): List<Product> {
    return this.orders.flatMap { it.products }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 3.12 Get used to new style
fun doSomethingStrangeWithCollection(collection: Collection<String>): Collection<String>? {

    val groupsByLength = collection. groupBy { s -> s.length }

    val maximumSizeOfGroup = groupsByLength.values.map { group -> group.size }.max()

    return groupsByLength.values.firstOrNull { group -> group.size == maximumSizeOfGroup }
}

