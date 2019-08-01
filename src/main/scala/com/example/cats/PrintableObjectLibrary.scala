package com.example.cats

object PrintableObjectLibrary {

  /**
   * ADT
   */
  final case class Cat(name: String, age: Int, color: String)

  /**
   * 01 / Define the TypeClass
   * @tparam A
   */
  trait Printable[A] {
    def format(value: A) : String
    def print(value : A) : Unit = println(format(value))
  }

  /**
   * 02/ Define some instances
   */
  object PrintableInstances {
    implicit val stringPrintable = new Printable[String] {
      override def format(value: String) = value
    }
    implicit val intPrintable = new Printable[Int] {
      override def format(value: Int) = value.toString
    }
    // add `printableinstance` for Cat
    implicit val catPrintable = new Printable[Cat] {
      override def format(cat: Cat): String = {
        val name = Printable.format(cat.name)
        val age = Printable.format(cat.age)
        val color = Printable.format(cat.color)
        s"$name is a $age year-old $color cat."
      }
    }
  }

  /**
   * 03/ Define interface object
   */
  object Printable {
    def format[A](value: A)(implicit p: Printable[A]) = p.format(value)
    def print[A] (value: A)(implicit p: Printable[A]) = p.print(value)
  }




}
