package Concurrency

object Basics extends App{

  class BankAccount(var amount : Int) {
    override def toString : String = "" + amount
  }

  // will cause race condtion
  def buy(account : BankAccount , thing : String , price : Int) ={
    account.amount -= price
//    println("I have bought " + thing)
//    println("my account is now "+ account)
  }


//  for( _ <- 1 to 10000){
//    val account = new BankAccount(50000)
//    val thread1 = new Thread(() => buySafe(account , "Phone" , 2000))
//    val thread2 = new Thread(() => buySafe(account, "Watch" , 500))

//    thread1.start()
//    thread2.start()
//    Thread.sleep(10)
//    if(account.amount != 47500) println("This is a race condition" + account.amount)
//  }

  // To solve race condition
  def buySafe(account : BankAccount, thing : String , price : Int) =
    account.synchronized{
      account.amount -= price
      println("I have bought " + thing)
      println("my account is now "+ account)
    }


  // Exercise
//1 . Create 50 inception threads and print(hello from thread No)

  def inceptionThreads(maxThread : Int, i: Int ) : Thread = new Thread(() =>{
    if(i < maxThread){
      val newThread = inceptionThreads(maxThread, i+1)
      newThread.start()
      newThread.join()
    }
    println("Hello thread no "+ i)
  }
  )
  inceptionThreads(20 , 1).start()


// 2

  var x = 0
  val thread = (1 to 100).map(_ => new Thread(() => x += 1))
  thread.foreach(_.start())
  thread.foreach(_.join())
  println(x)

//



}
