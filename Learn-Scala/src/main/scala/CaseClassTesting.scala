
object CaseClassTesting extends App{

  abstract class IdentityInfo{
    def mainRecordId: Long
    def isEmpty: Boolean
    def allIds: Set[Long]
  }

  case class SingleIdentitiy(id: Long, originFlags: Long, originalId: Option[String]) extends IdentityInfo{

    override def mainRecordId: Long = id

    override def isEmpty: Boolean = false

    override def allIds: Set[Long] = Set(id)
  }


  class DspIdentityInfo(id: Long, originFlags: Long, originalId: Option[String], val sameTransactionIds: Set[Long])
    extends SingleIdentitiy(id, originFlags, originalId) {
    override def mainRecordId: Long = id

    override def isEmpty: Boolean = false
  }

  val info = new DspIdentityInfo(1L , 2L , Some("OriginalId") , Set(1L))
  println(info.isInstanceOf[DspIdentityInfo])
  println(info.isInstanceOf[SingleIdentitiy])

  println(info)

}
