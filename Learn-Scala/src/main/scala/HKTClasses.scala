object HKTClasses extends App{

  trait HKT[F[_]]{
    def map[A , B](fa : F[A])(f: A => B): F[B]
  }

  implicit val ListHKT : HKT[List] = new HKT[List] {
    def map[A, B](fa: List[A])(f: A => B): List[B] = fa.map(f)
  }

  implicit val OptionHKT : HKT[Option] = new HKT[Option] {
    def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa.map(f)
  }

  val aList = List(1,2,3,4)
  val Num = Some(32)

  val mappedList : List[String] = implicitly[HKT[List]].map(aList)(_ + "!")
  val OptionNum : Option[String] = implicitly[HKT[Option]].map(Num)(_ + "!")




  println(mappedList)
  println(OptionNum)
}
