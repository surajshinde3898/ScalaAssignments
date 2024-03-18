import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object LaaSReporting extends App{

  val format  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
  val logEntry : List[LogEntry]  ={List(
    LogEntry(LocalDateTime.parse("2024-03-17 10:39:30.922", format), "Warn" , "Record: 1553944793225962981 was too big. Clearing TransactionLog"),
    LogEntry(LocalDateTime.parse("2024-03-17 11:39:30.922" ,format), "Warn" , "Record: 2605228887897662395 was too big. Clearing TransactionLog"),
    LogEntry(LocalDateTime.parse("2024-03-17 11:45:30.922", format), "Warn" , "Version mismatch, failing tuple. MainProfileId:8363117741939217348. Retry limit reached"),
    LogEntry(LocalDateTime.parse("2024-03-17 11:51:30.922", format), "Error", "Error in RemoteControl periodic run"),
    LogEntry(LocalDateTime.parse("2024-03-17 09:00:30.922", format), "Info", "Application Started"),
    LogEntry(LocalDateTime.parse("2024-03-17 09:10:30.922", format), "Debug", "Transaction Decoded")
  )
  }

  def sortOnLevelOrTimeStamp(logs : List[LogEntry] , sortOn : String): List[LogEntry] = sortOn match {
    case "level" => logs.sortBy(log => log.level)
    case "timestamp" => logs.sortBy(log => log.timeStamp)
  }


  def filterLogByLevel(logs: List[LogEntry], level : String) : List[LogEntry] = {
    logs.filter(log => log.level == level)
  }


  def countLogsByLevel(logs: List[LogEntry]): Map[String, Int] ={
    logs.groupBy(log => log.level).view.mapValues(_.size).toMap
  }

  def generateReport(logCount:Map[String, Int]) : String ={
    val report = new StringBuilder()
    report.append("Genrated Report \n")
    logCount.foreach {
      case (level, count) => report.append(s"severity level : $level , Number of Records : $count \n")
    }
    report.toString()
  }

  println("Sorted Logs")
  val sortedData  = sortOnLevelOrTimeStamp(logEntry , "timestamp")
  sortedData.foreach(println)

  println("Filtered logs")
  val filteredOnLevel = filterLogByLevel(logEntry,"Error")
  filteredOnLevel.foreach(println)

  println("Count")
  val logCount = countLogsByLevel(logEntry)
  logCount.foreach(println)

  println()
  val logReport = generateReport(logCount)
  println(logReport)


}
