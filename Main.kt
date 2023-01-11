package machine

import kotlin.system.exitProcess

class Ingredients(val water: Int, val milk : Int, val beans: Int, val price: Int)

val espresso = Ingredients(250, 0, 16, 4)
val latte = Ingredients(350, 75, 20, 7)
val cappuccino = Ingredients(200, 100, 12, 6)

var machineWater = 400
var machineMilk = 540
var machineBeans = 120
var machineCups = 9

class CoffeeMachine {
    val start = ""
        get() {
            println("Write action (buy, fill, take):")
            return field
        }

    var action = ""
        set(value) {
            when (value) {
                "buy" -> coffeeMachine.buy()
                "fill" -> fill()
                "take" -> coffeeMachine.money = 0
                "remaining" -> printMachineStatus()
                "exit" -> exitProcess(0)
                "1" -> {
                    if (ingredientsCheck(espresso) == "sufficient") updateMachineIngredients(espresso)
                    else println("Sorry, not enough ${ingredientsCheck(espresso)}!\n")
                }
                "2" -> {
                    if (ingredientsCheck(latte) == "sufficient") updateMachineIngredients(latte)
                    else println("Sorry, not enough ${ingredientsCheck(latte)}!\n")
                }
                "3" -> {
                    if (ingredientsCheck(cappuccino) == "sufficient") updateMachineIngredients(cappuccino)
                    else println("Sorry, not enough ${ingredientsCheck(cappuccino)}!\n")
                }
                else -> coffeeMachine.action
            }
            field = value
        }

    private fun buy() {
        println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:")
        when (readln()) {
            "1" -> {
                if (ingredientsCheck(espresso) == "sufficient") updateMachineIngredients(espresso)
                else println("Sorry, not enough ${ingredientsCheck(espresso)}!\n")
            }
            "2" -> {
                if (ingredientsCheck(latte) == "sufficient") updateMachineIngredients(latte)
                else println("Sorry, not enough ${ingredientsCheck(latte)}!\n")
            }
            "3" -> {
                if (ingredientsCheck(cappuccino) == "sufficient") updateMachineIngredients(cappuccino)
                else println("Sorry, not enough ${ingredientsCheck(cappuccino)}!\n")
            }
        }
        main()
    }

    var money = 550
        set(value) {
            if (value == 0) println("I gave you \$${money}")
            field = value
            main()
        }
}

val coffeeMachine = CoffeeMachine()

fun main() {
    coffeeMachine.start
    coffeeMachine.action = readln()
}

fun fill() {
    println("Write how many ml of water you want to add:")
    machineWater += readln().toInt()
    println("Write how many ml of milk you want to add:")
    machineMilk += readln().toInt()
    println("Write how many grams of coffee beans you want to add:")
    machineBeans += readln().toInt()
    println("Write how many disposable cups you want to add:")
    machineCups += readln().toInt()

    main()
}

fun updateMachineIngredients(coffee: Ingredients) {
    println("I have enough resources, making you a coffee!\n")
    machineWater -= coffee.water
    machineMilk -= coffee.milk
    machineBeans -= coffee.beans
    machineCups -= 1
    coffeeMachine.money += coffee.price
}

fun ingredientsCheck(coffee: Ingredients): String {
    if (machineWater - coffee.water < 0) return "water"
    else if (machineMilk - coffee.milk < 0) return "milk"
    else if (machineBeans - coffee.beans < 0) return "beans"
    else if (machineCups < 1) return "cups"
    return "sufficient"
}

fun printMachineStatus() {
    println("\nThe coffee machine has:\n" +
            "$machineWater ml of water\n" +
            "$machineMilk ml of milk\n" +
            "$machineBeans g of coffee beans\n" +
            "$machineCups disposable cups\n" +
            "\$${coffeeMachine.money} of money\n")

    main()
}