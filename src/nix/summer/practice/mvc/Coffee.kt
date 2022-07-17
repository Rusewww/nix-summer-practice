package nix.summer.practice.mvc

enum class Coffee(val water: Int, val milk: Int, val coffeeBeans: Int, val money: Int) {
    ESPRESSO(250, 0, 15, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6);
}