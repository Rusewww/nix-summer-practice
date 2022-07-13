package nix.summer.practice.mvc

class Controller(private val machine: CoffeeMachine) {

    private lateinit var view: View

    fun attachView(_view: View) {
        view = _view
    }

    fun command() {
        takeCommand(view.start().toString())
    }

    fun makeCoffee(type: Coffee) {
        when (type) {
            Coffee.ESPRESSO, Coffee.LATTE, Coffee.CAPPUCCINO -> {
                view.showMessage(machine.buy(type).responseString)
            }
        }
    }

    fun takeCommand(command: String) {
        when (command) {
            "buy" -> {
                view.buyCoffee()
            }
            "fill" -> {
                val res = view.getIngredients()
                fillRecourses(res)
            }
            "take" -> view.showMessage("I gave you ${machine.take().responseString}\n")
            "remaining" -> view.showMessage(machine.info().responseString)
            "exit" -> view.exit()
        }
    }

    private fun fillRecourses(ingredients: Ingredients) {
        machine.fill(ingredients)
        view.showMessage(machine.info().responseString)
    }

}