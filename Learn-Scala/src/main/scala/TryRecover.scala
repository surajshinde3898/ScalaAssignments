import scala.util.Try

object TryRecover extends App{

  def divide(dividend: Int, divisor: Int): Int = {
    dividend / divisor
  }


  val result = Try(divide(10, 0)).recover {
    case e: ArithmeticException => -1
  }

  println(s"Result: ${result.get}")







}
