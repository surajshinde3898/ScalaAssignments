

object LogEnum extends Enumeration {
  type logValue = Value

  val Warn, Debug, Error, Info = Value


  import LogEnum._

  def logInfo(log: logValue) = {
    val logValue = log match {
      case Warn => log.id
      case Debug => log
    }
    println(logValue)
  }


  def main(args: Array[String]): Unit = {
    logInfo(Debug)
  }
}