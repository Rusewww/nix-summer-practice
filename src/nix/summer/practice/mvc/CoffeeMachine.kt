package nix.summer.practice.mvc

data class Response(var responseString: String)


data class Ingredients(
    var water: Int = 0,
    var milk: Int = 0,
    var coffee: Int = 0,
    var disposableCups: Int = 0,
)

class CoffeeMachine(
    private var water: Int,
    private var milk: Int,
    private var coffee: Int,
    private var disposableCups: Int,
    private var money: Int
) {

    fun info(): Response {
        return Response(
            "\nThe coffee machine has: \n" +
                    "${this.water} ml of water; \n" +
                    "${this.milk} ml of milk; \n" +
                    "${this.coffee} g of coffee beans; \n" +
                    "${this.disposableCups} of disposable cups; \n" +
                    "${this.money} of money.\n"
        )
    }

    private fun check(coffeeType: String): Boolean {
        if (this.water - Coffee.valueOf(coffeeType).water >= 0
            && this.milk - Coffee.valueOf(coffeeType).milk >= 0
            && this.coffee - Coffee.valueOf(coffeeType).coffeeBeans >= 0
            && this.disposableCups > 0
        ) return true
        if (this.water - Coffee.valueOf(coffeeType).water < 0) println("Sorry, not enough water!")
        if (this.milk - Coffee.valueOf(coffeeType).milk < 0) println("Sorry, not enough milk!")
        if (this.coffee - Coffee.valueOf(coffeeType).coffeeBeans < 0) println("Sorry, not enough coffee beans!")
        if (this.disposableCups < 0) println("Sorry,  not enough disposable cups!")
        return false
    }

    fun buy(coffeeType: Coffee): Response {
        val check = this.check(coffeeType.name)
        return if (check) {
            this.water -= coffeeType.water
            this.milk -= coffeeType.milk
            this.coffee -= coffeeType.coffeeBeans
            this.money += coffeeType.money
            this.disposableCups -= 1
            Response("I have enough resources, making you a coffee!")
        } else {
            Response("Not enough ingredients, see console for details")
        }
    }

    fun fill(ingredients: Ingredients) {
        this.water += ingredients.water
        this.milk += ingredients.milk
        this.coffee += ingredients.coffee
        this.disposableCups += ingredients.disposableCups
    }

    fun take(): Response {
        val out = this.money
        this.money = 0
        return Response("$out")
    }
}