package nix.summer.practice.coffeemachine

fun main() {

    //Stage 1

    /*val startMsg = "Starting to make a coffee in NIX office"
    val grindingMsg = "Grinding coffee beans"
    val boilingMsg = "Boiling water"
    val mixingMsg = "Mixing boiled water with crushed coffee beans"
    val pouringCoffeeMsg = "Pouring coffee into the cup"
    val pouringMilkMsg = "Pouring some milk into the cup"
    val readyMsg = "Coffee is ready! Go to work!"
    println("${startMsg}\n${grindingMsg}\n${boilingMsg}\n${mixingMsg}\n${pouringCoffeeMsg}\n${pouringMilkMsg}\n${readyMsg}")*/

    //Stage 2

    /*println("Write how many cups of coffee you will need:")
    val cups = readLine()!!.toInt()
    val countOfWater = cups * 200
    val countOfMilk = cups * 50
    val countOfCoffee = cups * 15
    println("For $cups cups of coffee you will need:")
    println("$countOfWater ml of water")
    println("$countOfMilk ml of milk")
    println("$countOfCoffee g of coffee beans")*/

    //Stage 3

    /*println("Write how many ml of water the coffee machine has:")
    val water = readLine()!!.toInt()
    println("Write how many ml of milk the coffee machine has:")
    val milk = readLine()!!.toInt()
    println("Write how many grams of coffee beans the coffee machine has:")
    val coffee = readLine()!!.toInt()
    var cups = water / 200;
    if (cups > (milk / 50)) {
        cups = milk / 50
    }
    if (cups > (coffee / 15)) {
        cups = coffee / 15
    }
    println("Write how many cups of coffee you will need:")
    val needCups = readLine()!!.toInt()
    if (needCups == cups) {
        println("Yes, I can make that amount of coffee")
    }else if (needCups < cups){
        println("Yes, I can make that amount of coffee (and even ${cups - needCups} more than that)")
    } else {
        println("No, I can make only $cups cups of coffee")
    }*/

    val officeCoffee = CoffeeMachine(400, 540, 120, 9, 550)
    chooseAction(officeCoffee)
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

    private fun check(coffeeType: String?): Boolean {
        when (coffeeType) {
            "1" -> {
                if (this.water - 250 >= 0 && this.coffee - 15 >= 0 && this.disposableCups > 0) return true
                if (this.water - 250 < 0) {
                    println("Sorry, not enough water!")
                }
                if (this.coffee - 15 < 0) {
                    println("Sorry, not enough coffee beans!")
                }
                if (this.disposableCups < 0) {
                    println("Sorry,  not enough disposable cups!")
                }
            }
            "2" -> {
                if (this.water - 350 >= 0 && this.water - 75 >= 0 && this.coffee - 20 >= 0 && this.disposableCups > 0) return true
                if (this.water - 350 < 0) {
                    println("Sorry, not enough water!")
                }
                if (this.milk - 75 < 0) {
                    println("Sorry, not enough milk!")
                }
                if (this.coffee - 20 < 0) {
                    println("Sorry, not enough coffee beans!")
                }
                if (this.disposableCups < 0) {
                    println("Sorry,  not enough disposable cups!")
                }
            }
            "3" -> {
                if (this.water - 200 >= 0 && this.water - 100 >= 0 && this.coffee - 12 >= 0 && this.disposableCups > 0) return true
                if (this.water - 200 < 0) {
                    println("Sorry, not enough water!")
                }
                if (this.milk - 100 < 0) {
                    println("Sorry, not enough milk!")
                }
                if (this.coffee - 12 < 0) {
                    println("Sorry, not enough coffee beans!")
                }
                if (this.disposableCups < 0) {
                    println("Sorry, not enough disposable cups!")
                }
            }
            "back" -> return true
        }
        return false
    }

    fun buy(coffeeType: String?) {
        val check = this.check(coffeeType)
        if (check) {
            when (coffeeType) {
                "1" -> {
                    this.water -= 250
                    this.coffee -= 15
                    this.money += 4
                    this.disposableCups -= 1
                    println("I have enough resources, making you a coffee!")
                }
                "2" -> {
                    this.water -= 350
                    this.milk -= 75
                    this.coffee -= 20
                    this.money += 7
                    this.disposableCups -= 1
                    println("I have enough resources, making you a coffee!")

                }
                "3" -> {
                    this.water -= 200
                    this.milk -= 100
                    this.coffee -= 12
                    this.money += 6
                    this.disposableCups -= 1
                    println("I have enough resources, making you a coffee!")
                }
                "back" -> {
                    println("Back to menu")
                }
                else -> {
                    println("Wrong type of coffee!")
                }
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

}

fun chooseAction(machine: CoffeeMachine) {
    while (true) {
        println("Write action (buy, fill, take, remaining, exit):")
        val action = readLine()
        if (action == "exit") break
        when (action) {
            "buy" -> {
                println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
                val coffeeType = readLine()
                machine.buy(coffeeType)
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
}
