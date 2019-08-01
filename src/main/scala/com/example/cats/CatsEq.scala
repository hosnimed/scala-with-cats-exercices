package com.example.cats

object CatsEq {

  import cats.Eq

  import cats.instances.int._ // for Eq
  implicit val eqInt : Eq[Int] = Eq[Int]

  //Eq for custom types : date
  import java.util.Date
  import cats.instances.long._
  import cats.syntax.eq._
  implicit val eqDate : Eq[Date] = Eq.instance[Date]((d1: Date, d2:Date) => d1.getTime === d2.getTime)

  //Eq for Cat
  import PrintableObjectLibrary.Cat
  import cats.instances.string._
  implicit val eqCat : Eq[Cat] = Eq.instance[Cat]((c1: Cat, c2:Cat) => (c1.name.toUpperCase() === c2.name.toUpperCase) && (c1.age === c2.age) && (c1.color.toUpperCase === c2.color.toUpperCase))
}
