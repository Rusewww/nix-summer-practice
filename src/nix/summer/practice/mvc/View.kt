package nix.summer.practice.mvc

interface View {

    var controller: Controller
    fun start()
    fun buyCoffee()
    fun showMessage(info: String): String
    fun getIngredients(): Ingredients
    fun exit()
}