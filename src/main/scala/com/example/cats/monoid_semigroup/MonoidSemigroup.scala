package com.example.cats.monoid_semigroup

object MonoidSemigroup extends App {

  /**
   * monoid
   */
  import Implicits._
  val intMonoid = Monoid[Set[Int]]
  val stringMonoid = Monoid[Set[String]]

  private val ints: Set[Int] = intMonoid.combine(Set(1,2), Set(2,3))
  println(ints.mkString(", "))
  private val strings: Set[String] = stringMonoid.combine(Set("a", "b"), Set("b", "c"))
  println(strings.mkString(","))

  /**
   * monoid & semigroup in cats
   */
  import cats.Monoid
  import cats.Semigroup
  import cats.instances.string._
  import cats.instances.int._

  println( Semigroup.apply[Int].combine( 10, Monoid.apply[Int].empty))
  println( Monoid[Int].combineAll( 1 to 5))
  println( Semigroup[Int].combineAllOption(1 to 5))
  println( Semigroup[Int].combineN(1, 5))
  println( Monoid[String].combine("Hello", " Cats."))
  println( Semigroup[String].combineN("A", 5))

  import cats.syntax.semigroup._
  println(s" 1 |+| 9 |+| Monoid[Int].empty == ${ 1 |+| 9 |+| Monoid[Int].empty }")
  val hello = "Hel" |+| Monoid[String].empty |+| "lo!"
  println(s" Hello |+| Monoid[String].empty |+| Cats! == ${ hello }")

  /**
   * SuperAdder
   */
  trait SuperAdder[A]{
    def add(xs : List[A])(implicit monoid: Monoid[A]) : A
  }
  class FoldLeftSuperAdder[A] extends SuperAdder[A]{
    override def add(xs: List[A])(implicit monoid: Monoid[A]): A = xs.foldLeft(monoid.empty)(_ |+| _)
  }
  class MonoidSuperAdder[A] extends SuperAdder[A]{
    override def add(xs: List[A])(implicit monoid: Monoid[A]): A = monoid.combineAll(xs)
  }
  println(new FoldLeftSuperAdder[Int].add((1 to 9).toList))
  println(new MonoidSuperAdder[Int].add((1 to 9).toList))
  // List[Option[Int]]
  import cats.instances.option._
  println(new MonoidSuperAdder[Option[Int]].add(Some(5)::None::Some(4)::Some(1)::None::Nil))
  import cats.syntax.option._
  println(new MonoidSuperAdder[Option[Int]].add(5.some::none[Int]::4.some::1.some::none[Int]::Nil))

  /**
   * Custom type
   */
  //Order
  val o1 = Order(75.0d, 7d)
  val o2 = Order(25.0d, 3)
  println(s"$o1 + $o2 == ${ Implicits.Monoid[Order].combine(o1, o2) }")
  //map
  import cats.instances.map._
  val m1 = Map("a" -> 1, "b" -> 2)
  val m2 = Map("b" -> 3, "c" -> 3)
  println(s"$m1 + $m2 == ${ m1 |+| m2 }")
  //tuple
  import cats.instances.tuple._
  val t1 = ("ab", 12)
  val t2 = ("cd", 43)
  println(s"$t1 ++ $t2 ==  ${ t1 |+| t2 }")
  
}
