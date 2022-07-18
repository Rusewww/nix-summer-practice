package nix.summer.practice.mvc

import java.util.*
import kotlin.system.exitProcess

class TerminalView(override var controller: Controller) : View {

    override fun start() {
        println("Write action (buy, fill, take, remaining, exit):")
        val command = readLine().toString()
        controller.takeCommand(command)
    }

    override fun buyCoffee() {
        println("\nWhat do you want to buy? Espresso, latte, cappuccino, back - to main menu:")
        val coffeeType = readLine()?.uppercase(Locale.getDefault())
        if (coffeeType != null) {
            when (coffeeType) {
                "BACK" -> showMessage("Back to menu.\n")
                "ESPRESSO", "LATTE", "CAPPUCCINO" -> controller.makeCoffee(Coffee.valueOf(coffeeType))
                else -> showMessage("Wrong type of coffee!\n")
            }
        }
    }

    override fun showMessage(info: String): String {
        println(info)
        return "Displayed"
    }

    override fun getIngredients(): Ingredients {
        println("\nWrite how many ml of water you want to add:")
        val water = readLine()!!.toInt()
        println("Write how many ml of milk you want to add:")
        val milk = readLine()!!.toInt()
        println("Write how many grams of coffee beans you want to add:")
        val coffee = readLine()!!.toInt()
        println("Write how many disposable coffee cups you want to add:")
        val disposableCups = readLine()!!.toInt()
        return Ingredients(water, milk, coffee, disposableCups)
    }

    override fun exit() {
        println("Machine is off!")
        exitProcess(0)
    }
}