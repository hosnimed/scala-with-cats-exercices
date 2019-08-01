package com.example.cats

object PrintableWithCats {

  import PrintableObjectLibrary.Cat

  import cats.Show
  import cats.instances.int._
  import cats.instances.string._
  import cats.syntax.show._

  implicit val catshow : Show[Cat] = Show.show[Cat]((c:Cat) =>  s"${c.name.show} is a ${c.age.show} year-old ${c.color.show} cat.")
}
