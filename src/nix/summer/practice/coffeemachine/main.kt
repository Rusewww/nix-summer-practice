package nix.summer.practice.coffeemachine

import java.util.*

fun main() {
    val officeCoffee = CoffeeMachine(400, 540, 120, 9, 550)
    while(true){
        println("Write action (buy, fill, take, remaining, exit):")
        val action = readLine()
        if (action == "exit") break;
        if (action != null) {
            officeCoffee.command(action)
        }
    }
}

class CoffeeMachine(
    private var water: Int,
    private var milk: Int,
    private var coffee: Int,
    private var disposableCups: Int,
    private var money: Int
) {

    fun coffeeMachineInfo() {
        println(
            "\nThe coffee machine has:\n" +
                    "${this.water} ml of water\n" +
                    "${this.milk} ml of milk\n" +
                    "${this.coffee} g of coffee beans\n" +
                    "${this.disposableCups} of disposable cups\n" +
                    "${this.money} of money\n"
        )
    }

    private fun check(coffeeType: String): Boolean {
        if (coffeeType == "BACK") {
            return true
        } else {
            if (this.water - Coffee.valueOf(coffeeType).water >= 0
                && this.milk - Coffee.valueOf(coffeeType).milk >= 0
                && this.coffee - Coffee.valueOf(coffeeType).coffeeBeans >= 0
                && this.disposableCups > 0
            ) return true
            if (this.water - Coffee.valueOf(coffeeType).water < 0) println("Sorry, not enough water!")
            if (this.milk - Coffee.valueOf(coffeeType).milk < 0) println("Sorry, not enough milk!")
            if (this.coffee - Coffee.valueOf(coffeeType).coffeeBeans < 0) println("Sorry, not enough coffee beans!")
            if (this.disposableCups < 0) println("Sorry,  not enough disposable cups!")
        }
        return false
    }

    fun buy(coffeeType: String) {
        val check = this.check(coffeeType)
        if (coffeeType == "BACK") {
            println("Back to menu!")
        } else {
            if (check) {
                this.water -= Coffee.valueOf(coffeeType).water
                this.milk -= Coffee.valueOf(coffeeType).milk
                this.coffee -= Coffee.valueOf(coffeeType).coffeeBeans
                this.money += Coffee.valueOf(coffeeType).money
                this.disposableCups -= 1
                println("I have enough resources, making you a coffee!")
            }
        }
        println()
    }

    fun fill(water: Int, milk: Int, coffee: Int, disposableCups: Int) {
        this.water += water
        this.milk += milk
        this.coffee += coffee
        this.disposableCups += disposableCups
    }

    fun take() {
        println("I gave you ${this.money}\n")
        this.money = 0
    }

    fun command(action: String) {
        chooseAction(action, this)
    }
}

enum class Coffee(val water: Int, val milk: Int, val coffeeBeans: Int, val money: Int) {
    ESPRESSO(250, 0, 15, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6);
}

private fun chooseAction(action: String, machine: CoffeeMachine) {
    when (action) {
        "buy" -> {
            println("\nWhat do you want to buy? Espresso, latte, cappuccino, back - to main menu:")
            val coffeeType = readLine()?.uppercase(Locale.getDefault())
            if (coffeeType == "ESPRESSO" || coffeeType == "LATTE" || coffeeType == "CAPPUCCINO" || coffeeType == "BACK") {
                machine.buy(coffeeType)
            } else {
                println("Wrong type of coffee!\n")
            }
        }
        "fill" -> {
            println("\nWrite how many ml of water you want to add:")
            val water = readLine()!!.toInt()
            println("Write how many ml of milk you want to add:")
            val milk = readLine()!!.toInt()
            println("Write how many grams of coffee beans you want to add:")
            val coffee = readLine()!!.toInt()
            println("Write how many disposable coffee cups you want to add:")
            val disposableCups = readLine()!!.toInt()
            machine.fill(water, milk, coffee, disposableCups)
            println()
        }
        "take" -> machine.take()
        "remaining" -> machine.coffeeMachineInfo()
        else -> println("Wrong action!")
    }
}
