object CaseClassWithTrait extends App{

  trait Shape
  case class CircleSize(radius : Double) extends Shape
  case class TriangleSize(width : Double , height : Int) extends Shape

  def caculateShape(shape: Shape): Double = shape match{
    case CircleSize(redius) => 2* math.Pi * redius
    case TriangleSize(width, height) => 2* (width + height)
  }

 val shapes : List[Shape] = List(
   CircleSize(2.5),
   TriangleSize(3.5 , 5)
 )

  shapes.foreach(shape => println(s"shape of $shape : ${caculateShape(shape)}"))
}
