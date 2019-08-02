package com.example.cats.intro

import cats.instances.string._
import cats.syntax.semigroup._

object Intro extends App {
  println("Hello " |+| "Cats!")

  import PrintableObjectLibrary.{Cat, Printable}
  import PrintableObjectLibrary.PrintableInstances._

  val cat = Cat("TOM", 10, "RED")

  Printable.print(10)
  Printable.print("Cats!")
  Printable.print(cat)

  import PrintableSyntaxLibrary.PrintableOps
  10.print
  "Cats!".print
  cat.print

  import MeetCats.intShow
  println(intShow.show(10))
  import MeetCats.stringShow
  println(stringShow.show("Cats!"))
  import cats.syntax.show._
//  println(10.show)
  println("Cats!".show)
  import java.util.Date
//  import MeetCats.dateShow
  import MeetCats.dateShowFunction
  private val date = new Date(System.currentTimeMillis())
  println(date.show)

  //printable with cats
  import PrintableWithCats.catshow
  println(cat.show)


  // equality with cats
  import CatsEq.eqInt
  import cats.syntax.eq._
  println(s"123 eqv 123 ==> ${eqInt.eqv(123, 123)}")
  println(s"123 neqv 1234 ==> ${eqInt.neqv(123, 1234)}")
  //  println(s"123 eqv String(\"abc\") ==>  ${eqInt.eqv(123, "abc")}") //compile error
  println(s"123 === 123 ==> ${123 === 123}")
  println(s"123 =!= 1234 ==> ${123 =!= 1234}")

  //option[Int]
  import cats.instances.option._
  println(s"Some(123) eqv None ==> ${ (Some(123) : Option[Int]) === (None: Option[Int])}")
  println(s"Option.apply(1) === Option.empty ==> ${ Option.apply(1) === Option.empty}")
  import cats.syntax.option._ // for some and none
  println(s"123.some === none[Int] ==> ${ 123.some === none[Int]}")

  //custom type: Date
  import CatsEq.eqDate
  private val l = System.currentTimeMillis()
  val d1 = new Date(l)
  val d2 = new Date(l + 10000)
  println(s"$d1 === $d1   ==> ${d1 === d1}")
  println(s"$d1 =!= $d2   ==> ${d1 =!= d2}")

  // custom type : Cat
  import CatsEq.eqCat
  val c1 = Cat("A", 10, "RED")
  val c2 = Cat("B", 20, "BLACK")
  println(s"${c1.show} === ${c1.show} ==> ${c1 === c1}")
  println(s"${c1.show} =!= ${c2.show} ==> ${c1 =!= c2}")
  // option[cat]
  println(s"${c1.some} === ${c1.some} ==> ${c1.some === c1.some}")
  println(s"${c1.some} =!= ${c2.some} ==> ${c1.some =!= c2.some}")
  println(s"${c1.some} =!= ${none[Cat]} ==> ${c1.some =!= none[Cat]}")
}
