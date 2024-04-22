package Concurrency

object ThreadCommunicationExcercise extends App{

  //1 notifyAll

  def notifyAllThread()={
    val bell = new Object

    (1 to 10 ).foreach(i => new Thread(() => {
      bell.synchronized{
        println(s"[Thread $i] waiting")
        bell.wait()
        println(s"[Thread $i] waiting period is over")
      }
    }).start())

    new Thread(() => {
      Thread.sleep(2000)
      println("Notify all")
      bell.synchronized{
        bell.notifyAll()
      }
    }).start()
  }

  notifyAllThread()

}
