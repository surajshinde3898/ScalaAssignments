object Genrics extends App {

  class Box[A](val content : A) {
    def unit ={
      println(content)
    }
  }

  class Gen1[A , B](val First : A , val Second : B){
    def makeString = {
      s"first : $First , second : $Second"
    }
  }

  val twoInput = new Gen1[String , Int]("Suraj", 432)

  print(twoInput.First , twoInput.Second)


  val intBox = new Box[Int](4)

  val stringBox = new Box[String]("Suraj")

  val floatBox = new Box[Double](3.533)

  println(intBox.content , stringBox.content , floatBox.content)



}
