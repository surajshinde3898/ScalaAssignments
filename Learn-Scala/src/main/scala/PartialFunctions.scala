object PartialFunctions extends App{
  val selectShape = new PartialFunction[Int , String]{
    override def isDefinedAt(x: Int): Boolean = x == 1 || x == 2 || x == 3

    override def apply(x: Int): String = x match {
      case 1 => "Triangle"
      case 2 => "Circle"
      case 3 => "Square"
    }
  }

  println(selectShape.isDefinedAt(2))

  val chatbot : PartialFunction[String , String] ={
    case "Hello" => "Hello, I am Chitti"
    case "Can you beat chat GPT" => "No, But one day I'll beat chat gpt"
  }

  scala.io.Source.stdin.getLines().map(chatbot).foreach(println)
}
