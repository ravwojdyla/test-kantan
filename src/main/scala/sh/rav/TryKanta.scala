package sh.rav

import kantan.csv._
import kantan.csv.ops._
import kantan.csv.generic._

package object person {
  case class PersonOption(age:Int,first:String,last:Option[String])
  implicit val personOptionDecoder: RowDecoder[PersonOption] =
    RowDecoder.decoder(0,1,2)(PersonOption.apply)
}

object TryKantan {

  // See tests in TestTryKantan
  import person._
  def main(args: Array[String]): Unit = {
    """|1,"rav","foo"
       |3,"obama",""""".stripMargin.asCsvReader[PersonOption](',', false).foreach(println _)
  }
}
