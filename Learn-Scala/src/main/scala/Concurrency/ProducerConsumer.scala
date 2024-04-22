package Concurrency

import scala.collection.mutable
import scala.util.Random

object ProducerConsumer extends App{
  class SimpleContainer {
    var value : Int = 0
    def isEmpty : Boolean = value == 0
    def set(newValue : Int) = value = newValue
    def get ={
      val result = value
      value = 0
      result
    }
  }

  def producerCons() ={
    val container = new SimpleContainer

    val consumer = new Thread(() =>{
      while(container.isEmpty){
        println("container is empty")
      }

      println("Consumer have consumed value " + container.get)
    })

    val producer = new Thread(() => {
      Thread.sleep(500)
      val value = 43
      println("produced value  "+ value)
      container.set(value)
    })
    consumer.start()
    producer.start()
  }

  //producerCons()

  // wait and notify
  def smartProdCons ={
    val container = new SimpleContainer
    val consumer =  new Thread(() => {
      container.synchronized {
        container.wait()
      }
      println("Consumed value "+ container.get)
    })

    val producer = new Thread(() => {
      Thread.sleep(2000)
      val value = 42

      container.synchronized{
        println("producing value "+ value)
        container.set(value)
        container.notify()
      }
    })

    consumer.start()
    producer.start()
  }

  //smartProdCons


  // producer [, , , ]  consumer

  def prodConsBuffer(): Unit = {
    val buffer : mutable.Queue[Int] = new mutable.Queue[Int]
    val capacity = 3

    val consumer = new Thread(()=>{
      val random = new Random()
      while(true){
        buffer.synchronized{
          if(buffer.isEmpty) {
            println("[Consumer] Waiting for producer")
            buffer.wait()
          }
          val x = buffer.dequeue()
          println("[Consumer] Consumed value " + x)
          buffer.notify()
        }

        Thread.sleep(random.nextInt(500))
      }
    })

    val producer = new Thread(() => {
      val random = new Random()
      var i = 0
      while(true){
        buffer.synchronized{
          if(buffer.size == capacity) {
            println("[Producer] Buffer is full ")
            buffer.wait()
          }

          println("[Producer] producing " + i)
          buffer.enqueue(i)
          buffer.notify()
          i += 1
        }
        Thread.sleep(random.nextInt(500))
      }
    })

    consumer.start()
    producer.start()

  }

  //prodConsBuffer()


  // multiple producer and multiple consumer

  class Consumer(id : Int, buffer : mutable.Queue[Int]) extends Thread {
    override def run() = {
        val random = new Random()
        while(true){
          buffer.synchronized{
            while(buffer.isEmpty) {
              println(s"[Consumer $id] Waiting for producer")
              buffer.wait()
            }
            val x = buffer.dequeue()
            println(s"[Consumer $id] Consumed value " + x)
            buffer.notify()
          }

          Thread.sleep(random.nextInt(500))
        }
    }
  }

  class Producer(id : Int , buffer : mutable.Queue[Int], capacity : Int) extends Thread{
    override def run() = {
      val random = new Random()
      var i = 0
        while(true){
          buffer.synchronized{
            while(buffer.size == capacity) {
              println(s"[Producer $id] Buffer is full ")
              buffer.wait()
            }

            println(s"[Producer $id] producing " + i)
            buffer.enqueue(i)
            buffer.notify()
            i += 1
          }
          Thread.sleep(random.nextInt(500))
        }
      }
  }

  def multiProducer(nConsumer : Int , nProducer : Int) = {
    val buffer : mutable.Queue[Int] = new mutable.Queue[Int]
    val capacity = 3

    (1 to nConsumer).foreach(n => new Consumer(n , buffer).start())
    (1 to nProducer).foreach(n => new Producer(n , buffer, capacity).start())
  }

  multiProducer(3, 3)
}
