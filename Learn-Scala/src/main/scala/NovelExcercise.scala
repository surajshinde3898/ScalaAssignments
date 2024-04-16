object NovelExcercise extends App{
  val writer = new Writer("suraj", "shinde", 1998)
  val noval = new Noval("Marval", 2019, writer)

  println(noval.authorage)
  println(noval.isWritenBy())
  val newNoval = noval.copy(2024)
  println(newNoval.authorage)

  val supperAdd: Int => (Int => Int) = (x : Int) => (y: Int) => x + y
  val add = supperAdd(3)
  println(add(3))
  println(supperAdd(3)(10))


}

class Writer(firstName: String, lastName: String, val year: Int) {
  def fullname = firstName + " " + lastName
}

class Noval(name: String, yearOfRelease: Int, author: Writer) {
  def authorage = yearOfRelease - author.year

  def isWritenBy() = s"$name is written by ${author.fullname}"

  def copy(newYear: Int) = new Noval(name, newYear, author)
}
