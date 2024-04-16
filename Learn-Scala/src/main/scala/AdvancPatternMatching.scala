object AdvancPatternMatching extends App{

  //infix pattern (works only when we have 2 things in pattern)

  case class Or[A, B](a: A, b: B)
  val either = Or(2 , "Two")

  val humanDiscription = either match{
    case number Or string => s"$number is written as $string"
  }
  println(humanDiscription)

//
  abstract class MyList[+A] {
    def head : A = ???
    def tail : MyList[A] = ???
  }

  case object Empty extends MyList[Nothing]
  case class Cons[+A](override val head : A, override val tail:MyList[A]) extends MyList[A]

  object MyList{
    def unapplySeq[A](list:MyList[A]) : Option[Seq[A]] = {
      if(list == Empty) Some(Seq.empty)
      else {
        val newData =unapplySeq(list.tail).map(list.head +: _)
        println(newData)
        newData
      }
    }
  }

  val myList : MyList[Int] = Cons(1 , Cons(2 , Cons(3 , Empty)))

  val decompossed = myList match {
    case MyList(1 , 2 , _*) => "strting with 1 , 3"
    case _ => "something else"
  }

  println(decompossed)

  case class Persons(name : String)
  val bob = new Persons("Bob")
  // custom return type for unapply

  abstract class wrapper[T] {
    def isEmpty : Boolean
    def get : T
  }

  object PersonWrapper{
    def unapply(persons: Persons): wrapper[String] = new wrapper[String] {
      override def isEmpty: Boolean = false

      override def get: String = persons.name
    }
  }

  println(bob match {
    case PersonWrapper(n) => s"This person name is $n"
    case _ => "an alien"
  })


}
