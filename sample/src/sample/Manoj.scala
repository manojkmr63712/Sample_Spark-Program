package sample

import java.util.{Date, Locale}
import java.text.DateFormat
import java.text.DateFormat._

object Manoj {
  def sum(a:Integer,b:Integer): Integer = {
    if (a%2==0){
      println("There is an chance")
      return a+b
    }
    else{
      println("Its an odd")
      return a+b
    }        
  }
  def date(){
    val now = new Date
    val df = getDateInstance(LONG, Locale.FRANCE)
    println(df format now)
  }
  def main(args: Array[String]) {
    var data = sum(5,6)
    println(data)
    val cell = new Reference[Int]
    cell.set(13)
    println("Reference contains the half of " + (cell.get * 2))
  }
}