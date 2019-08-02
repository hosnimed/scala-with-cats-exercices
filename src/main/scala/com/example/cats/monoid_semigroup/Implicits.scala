package com.example.cats.monoid_semigroup

object Implicits {

  trait Semigroup[A] {
    def combine(x:A, y:A) : A
  }
  trait Monoid[A] extends Semigroup[A] {
    def empty : A
  }
  object Monoid {
    def apply[A](implicit monoid: Monoid[A]): Monoid[A] = monoid
  }

  implicit val booleanAndMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = false
    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  implicit val booleanOrMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = false
    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }

  implicit def setUnionMonoid[A] : Monoid[Set[A]] = new Monoid[Set[A]]{
    override def empty = Set.empty[A]
    override def combine(xs: Set[A], ys: Set[A]) = xs union ys
  }

  implicit def setIntersectionSemigroup[A] : Semigroup[Set[A]] = new Semigroup[Set[A]]{
    override def combine(xs: Set[A], ys: Set[A]) = xs intersect  ys
  }

  //custom type
  case class Order(totalCost: Double, quantity: Double)
  implicit val doubleMonoid : Monoid[Double] = new Monoid[Double] {
    override def empty: Double = 0.0d
    override def combine(x: Double, y: Double): Double = x + y
  }
  implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
    override def empty: Order = Order(0d, 0d)
    override def combine(o1: Order, o2: Order): Order =
      Order(
        totalCost= Monoid[Double].combine(o1.totalCost, o2.totalCost),
        quantity = Monoid[Double].combine(o1.quantity, o2.quantity))
  }

}
