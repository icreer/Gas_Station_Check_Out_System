import java.util.Random
import kotlin.math.roundToLong

fun main(args: Array<String>) {
    //println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    //println("Program arguments: ${args.joinToString()}")
    val id = Main().welcome()
    var AddToTotal = true
    var havePaid = true
    while (havePaid){
        println("Please tell me the type of item you want to buy: \n"
        + " 1. Gas \n"
        + " 2. Food \n"
        + " 3. Car items \n"
        + " 4. Other \n"
        + " 5. Checkout")
        val customizer = readLine()
        if (customizer == "5"){
            havePaid = false
        }
        else if (customizer == "1"){
            Items().gas(id, AddToTotal)
        }
        else if (customizer == "2"){
            Items().food(id, AddToTotal)
        }
        else if (customizer == "3"){
            Items().CarProduce(id, AddToTotal)
        }
        else if (customizer == "4"){
            Items().other(id, AddToTotal)
        }
        else{
            println("Sorry that is not an option")
        }

    }
}

data class tranzaction( val userID : String ){
    var total : Double = 0.0
    var itemtotal : Double = 0.0
    val itemscost: MutableList<Double> = mutableListOf()
    val item : MutableList<String> = mutableListOf()
}

class Main(){
    fun welcome() : String{
        println("Welcome to the store!!!")
        println("Please enter in your user id: ")
        val userid = readLine().toString()
        return userid
    }
}

val random = Random()
fun rand(from: Double, to: Double) : Double{
    return random.nextDouble(to-from) + from .roundToLong()
}

class Items(){
    fun gas( userid: String, AD: Boolean) : Boolean{
        println("Please enter the pump you are on: ")
        val pumpnumber = readLine().toString()
        tranzaction(userid).item.add("Gass")
        println("Please select an option how much you want to pay: "
                + "1. A fix amount\n"
                + "2. The remader after buying someother items ")
        val gas_option = readLine().toString().toInt()
        if (gas_option ==  1){
            println("Please enter the amount of gas you want to buy: ")
            val gasamount = readLine().toString().toDouble()
            tranzaction(userid).itemscost.add(gasamount)
            println("You are putting " + gasamount + " to pump " + pumpnumber)
            tranzaction(userid).itemtotal = tranzaction(userid).itemtotal + gasamount
            return true
        }
        else if (gas_option == 2){
            println("Please enter the total amount you want to spend:")
            val spendamount = readLine().toString().toDouble()
            println("Please keep on shopping as long as you stay under the amount you enter" +
                    "you will be told the amount of gas you can pump into pump number" + pumpnumber )
            tranzaction(userid).total = spendamount
            return false
        }
        else{
            println("That is not an option")
            return true
        }
    }

    fun food( userid:String, AD: Boolean){
        println("Please enter in the food you are getting: ")
        val items = readLine().toString()
        tranzaction(userid).item.add(items)
        val cost = rand(0.01,10.0)
        println("The cost for " + items + " is " + cost)
        tranzaction(userid).itemscost.add(cost)
        tranzaction(userid).itemtotal = tranzaction(userid).itemtotal + (cost * 1.06)
    }
    fun CarProduce( userid: String, AD:Boolean){
        println("Please enter in the Car item you are getting: ")
        val item = readLine().toString()
        tranzaction(userid).item.add(item)
        val cost = rand(10.01,100.0)
        println("The cost for " + item + " is " + cost)
        tranzaction(userid).itemscost.add(cost)
        tranzaction(userid).total = tranzaction(userid).total + (cost * 1.10)
    }
    fun other (userid:String, AD:Boolean){
        println("Please enter in the item you are getting: ")
        val item = readLine().toString()
        tranzaction(userid).item.add(item)
        val cost = rand(0.01,1000.0)
        println("The cost for " + item + " is " + cost)
        tranzaction(userid).itemscost.add(cost)
        tranzaction(userid).total = tranzaction(userid).total + (cost * 1.15)
    }
}

class Receipt(){
    fun showtotal(){

    }
    fun paymenttype(){

    }
    fun printreipt(){

    }

}
