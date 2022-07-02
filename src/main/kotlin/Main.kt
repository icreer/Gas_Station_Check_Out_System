import java.util.Random
import kotlin.math.roundToLong

fun main(args: Array<String>) {
    //println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    //println("Program arguments: ${args.joinToString()}")
    val id = Main().welcome()

    var havePaid = true
    var CTotal = 0.0
    var paytype = ""
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
            Items().gas(id)
        }
        else if (customizer == "2"){
            Items().food(id)
        }
        else if (customizer == "3"){
            Items().CarProduce(id)
        }
        else if (customizer == "4"){
            Items().other(id)
        }
        else{
            println("Sorry that is not an option")
        }

    }
    CTotal = Receipt().showtotal(id)
    paytype= Receipt().paymenttype(id)

    Receipt().printreipt(id,CTotal,paytype)
}

data class Tranzaction( val userID : String ){
    var total : Double = 0.0
    var itemtotal : Double = 0.0
    var itemscost: MutableList<Double> = mutableListOf()
    var item : MutableList<String> = mutableListOf()
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
    fun gas( userid: String) : Boolean{
        println("Please enter the pump you are on: ")
        val pumpnumber = readLine().toString()
        Tranzaction(userid).item.add("Gass")
        println("Please select an option how much you want to pay: \n"
                + "1. A fix amount\n"
                + "2. The remader after buying someother items ")
        val gas_option = readLine().toString().toInt()
        if (gas_option ==  1){
            println("Please enter the amount of gas you want to buy: ")
            val gasamount = readLine().toString().toDouble()
            Tranzaction(userid).itemscost.add(gasamount)
            println("You are putting " + gasamount + " to pump " + pumpnumber)
            Tranzaction(userid).itemtotal = Tranzaction(userid).itemtotal + gasamount + 1
            return true
        }
        else if (gas_option == 2){
            println("Please enter the total amount you want to spend:")
            val spendamount = readLine().toString().toDouble()
            println("Please keep on shopping as long as you stay under the amount you enter" +
                    "you will be told the amount of gas you can pump into pump number" + pumpnumber )
            Tranzaction(userid).total = spendamount
            return false
        }
        else{
            println("That is not an option")
            return true
        }
    }

    fun food( userid:String){
        println("Please enter in the food you are getting: ")
        val items = readLine().toString()
        Tranzaction(userid).item.add(items)
        val cost = rand(0.01,10.0)
        println("The cost for " + items + " is " + cost)
        Tranzaction(userid).itemscost.add(cost)
        Tranzaction(userid).itemtotal = Tranzaction(userid).itemtotal + (cost * 1.06)
    }
    fun CarProduce( userid: String){
        println("Please enter in the Car item you are getting: ")
        val item = readLine().toString()
        Tranzaction(userid).item.add(item)
        val cost = rand(10.01,100.0)
        println("The cost for " + item + " is " + cost)
        Tranzaction(userid).itemscost.add(cost)
        Tranzaction(userid).itemtotal = Tranzaction(userid).itemtotal + (cost * 1.10)
    }
    fun other (userid:String){
        println("Please enter in the item you are getting: ")
        val item = readLine().toString()
        Tranzaction(userid).item.add(item)
        val cost = rand(0.01,1000.0)
        println("The cost for " + item + " is " + cost)
        Tranzaction(userid).itemscost.add(cost)
        Tranzaction(userid).itemtotal = Tranzaction(userid).itemtotal + (cost * 1.15)
    }
}

class Receipt(){
    fun showtotal(userid: String) :Double{
       val totals = Tranzaction(userid).total
       val itemtotals = Tranzaction(userid).itemtotal
        if (totals == 0.0){
            return itemtotals
        }
        else{
            if (totals < itemtotals) {
                println("You will not be able to get gas unless you add more to prepay")
                return 0.0
            }
            else {
                println("That amount of gas you are get is $" + (totals - itemtotals))
                Tranzaction(userid).itemscost.add(totals - itemtotals)
                return totals
            }
        }
    }
    fun paymenttype( userid: String) :String{
        println("Please select your payment type:\n"
                + "1. Credit / Debit\n"
                + "2. Cash\n"
                + "3. Gift Card\n"
                + "4. Check\n")
        val payt = readLine().toString().toInt()
        var returnstring : String  = ""
        if (payt == 1){
            returnstring = "Credit / Debit"
        }
        else if (payt == 2){
            returnstring = "Cash"
        }
        else if (payt == 3){
            returnstring = "Gift Card"
        }
        else if (payt == 4){
            returnstring = "Check"
        }

        return returnstring
    }
    fun printreipt(userid: String, amount: Double, paytype: String){
        val itemsize = Tranzaction(userid).item.size
        for (i in 0..itemsize-1 ){
            println(Tranzaction(userid).item[i]+ "         " + Tranzaction(userid).itemscost[i]  )
        }
        println("Total: " + amount)
        println("You payed with "+ paytype)

    }

}
