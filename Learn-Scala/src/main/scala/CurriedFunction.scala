object CurriedFunction extends App{

  //1
  def calculateTotalCost(unitPrice: Double)(quantity: Int): Double = {
    unitPrice * quantity
  }

  // Curried function usage
  val calculateTotalCostForApples = calculateTotalCost(2.5) _
  val totalCostForApples = calculateTotalCostForApples(5)
  println("Total cost for apples: " + totalCostForApples)


}
