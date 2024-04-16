object LearnCaseClass extends App{

  case class Shape(name : String , sides : Int)

  def describeShape(shape: Shape) = shape match {
    case Shape("Circle" , _) => "A cicle has no sides"
    case Shape("Triangle", _) => s"A Trianngle has ${shape.sides} sides"
    case Shape("Rectangle",_) => s"A Rectangle has ${shape.sides} sides"
  }

  val circle =   Shape("Circle", 0)
  val triangle =  Shape("Triangle", 3)
  val Rectangle =  Shape("Rectangle", 4)

  println(describeShape(circle))
  println(describeShape(triangle))
  println(describeShape(Rectangle))

}
