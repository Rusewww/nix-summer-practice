package nix.summer.practice.mvc

fun main() {
    val officeCoffee = CoffeeMachine(400, 540, 120, 9, 550)
    val controller = Controller(officeCoffee)
    println("Choose the variant of menu (console or swing):")
    when (readLine()) {
        "console" -> {
            val terminalView = TerminalView(controller)
            controller.attachView(terminalView)
            while (true) {
                controller.command()
            }
        }
        "swing" -> {
            val swingView = SwingView(controller)
            controller.attachView(swingView)
            controller.command()
        }
        else -> println("Wrong action!")
    }

}