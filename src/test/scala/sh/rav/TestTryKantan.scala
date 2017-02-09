package sh.rav

import org.scalatest.{FlatSpec, Matchers}
import kantan.csv._
import kantan.csv.ops._
import kantan.csv.generic._
import person._

class TestTryKantan extends FlatSpec with Matchers {

  "kantan" should "parse single record valid string" in {
    """|1,"rav","foo"""".stripMargin.asCsvReader[PersonOption](',', false).toSeq should
      contain theSameElementsAs Vector(Success(PersonOption(1, "rav", Some("foo"))))
  }

  it should "parse multi record valid strings" in {
    """|1,"rav","foo"
       |3,"ariel","bar"""".stripMargin.asCsvReader[PersonOption](',', false).toSeq should
       contain theSameElementsAs Vector(Success(PersonOption(1, "rav", Some("foo"))),
                                        Success(PersonOption(3, "ariel", Some("bar"))))
  }

  it should "parse single record with empty string last value" in {
    """|1,"rav",""""".stripMargin.asCsvReader[PersonOption](',', false).toSeq should
      contain theSameElementsAs Vector(Success(PersonOption(1, "rav", None)))
  }


  it should "parse multi record with empty string as last value in first record" in {
    """|1,"rav",""
       |3,"ariel","bar"""".stripMargin.asCsvReader[PersonOption](',', false).toSeq should
       contain theSameElementsAs Vector(Success(PersonOption(1, "rav", None)),
                                        Success(PersonOption(3, "ariel", Some("bar"))))
  }


  it should "parse multi record with empty string as last value in second record" in {
    """|1,"rav","foo"
       |3,"ariel",""""".stripMargin.asCsvReader[PersonOption](',', false).toSeq should
       contain theSameElementsAs Vector(Success(PersonOption(1, "rav", Some("foo"))),
                                        Success(PersonOption(3, "ariel", None)))
  }

  it should "parse multi record with empty string as last value in both records" in {
    """|1,"rav",""
       |3,"ariel",""""".stripMargin.asCsvReader[PersonOption](',', false).toSeq should
       contain theSameElementsAs Vector(Success(PersonOption(1, "rav", None)),
                                        Success(PersonOption(3, "ariel", None)))
  }

}
