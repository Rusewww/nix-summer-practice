package nix.summer.practice.chatbot

fun main() {
    //Stage 1
    val botName = "BotRusewww";
    val yearOfCreating = 2022;
    println("Hello! My name is $botName.\nI was created in $yearOfCreating.");

    //Stage 2
    println("Please, remind me your name.");
    val yourName = readLine();
    println("What a great name you have, $yourName!");

    //Stage 3
    println("Let me guess your age.\nEnter remainders of dividing your age by 3, 5 and 7.")
    val remainder3 = readLine()!!.toInt();
    val remainder5 = readLine()!!.toInt();
    val remainder7 = readLine()!!.toInt();
    val age = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;
    println("Your age is $age; that's a good time to start programming!");

    //Stage 4
    println("I will prove to you that I can count to any number you want:");
    val userNumber = readLine()!!.toInt();
    var i = 1;
    while(i <= userNumber){
        println("$i!");
        i++;
    }

    //Stage 5

    var test = false;
    val rightAnswer = 2;
    while(!test){
        println("What programming language are we learning during our summer practice?");
        println("1. C/C++\n2. Kotlin\n3. Python\n4. JavaScript");
        if (rightAnswer == readLine()!!.toInt()){
            test = true;
            println("Great, you right!");
        } else {
            println("Please, try again.");
            continue;
        }
    }
    println("Goodbye, have a nice day!");
}