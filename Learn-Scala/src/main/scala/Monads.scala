object Monads extends App{


  def increment(x: Int): Option[Int] = Some(x + 1)

  // Define a value wrapped in an Option
  val optionValue = Some(5)

  // Left Identity: unit(x).flatMap(f) == f(x)
  val leftIdentityResult = Option(5).flatMap(increment)
  val directResult = increment(5)
  println("Left Identity Result: " + (leftIdentityResult == directResult)) // Should print: true

  // Right Identity: m.flatMap(unit) == m
  val rightIdentityResult = optionValue.flatMap(Some(_))
  println("Right Identity Result: " + (rightIdentityResult == optionValue)) // Should print: true

  // Associativity: m.flatMap(f).flatMap(g) == m.flatMap(x => f(x).flatMap(g))
  val associativityResult1 = optionValue.flatMap(increment).flatMap(increment)
  val associativityResult2 = optionValue.flatMap(x => increment(x).flatMap(increment))
  println("Associativity Result: " + (associativityResult1 == associativityResult2)) // Should print: true


  // Exercise
  class LazyMonad[+A](colleaction : => A){
    def value :A = colleaction
    def flatMap[B](f :(=> A) => LazyMonad[B]) :LazyMonad[B] = f(colleaction)
  }

  object LazyMonad{
    def apply[A](collection: => A): LazyMonad[A] = new LazyMonad(collection)
  }

  def multiply(x: => Int): LazyMonad[Int] = LazyMonad(x * 2)

  val lazyMonads = LazyMonad(10)
  val result: LazyMonad[Int] = lazyMonads.flatMap(multiply)

  println(result.value)

}
