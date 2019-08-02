package com.example.cats.intro

object MeetCats {

  import cats.Show
  import cats.instances.int.catsStdShowForInt
  import cats.instances.string.catsStdShowForString

  val intShow : Show[Int] = Show.apply[Int]
  val stringShow : Show[String] = Show.apply[String]

  //implement a Show for Date
  import java.util.Date
  //method 1
  implicit val dateShow : Show[Date] = new Show[Date] {
    override def show(t: Date): String = s"${t.getTime} Unix time"
  }
  //method 2
  implicit val dateShowFunction : Show[Date] = Show.show[Date]((t:Date) => s"${t.getTime} Unix time")
}
