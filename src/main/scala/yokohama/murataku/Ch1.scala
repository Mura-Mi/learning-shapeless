package yokohama.murataku

import shapeless._

case class Employee(name: String, number: Int, manager: Boolean)
case class IceCream(name: String, numCherries: Int, inCone: Boolean)

case class Red()
case class Amber()
case class Green()

trait CsvEncoder[A] {
  def encode(value: A): List[String]
  def apply(value: A) = encode(value)
}

object CsvEncoder {
  def apply[A](implicit enc: CsvEncoder[A]):CsvEncoder[A] = enc
  def instance[A](func: A => List[String]): CsvEncoder[A] = value => func(value)
}

sealed trait Shape
final case class Rectangle(width: Double, height: Double) extends Shape
final case class Circle(radius: Double) extends Shape

object Ch1 extends App {
  lazy val genericEmp = Generic[Employee].to(Employee("Ohta", 33, false))
  lazy val genericIce = Generic[IceCream].to(IceCream("Vanilla", 0, true))

  def genericCsv(gen: String :: Int :: Boolean :: HNil): List[String] = 
    List(gen(0), gen(1), gen(2)).map(_.toString)

  println(genericCsv(genericEmp))

  println(Generic[IceCream].from(genericEmp))

  type Light = Red :+: Amber :+: Green :+: CNil

  val red: Light = Inl(Red())
  val amber: Light = Inr(Inl(Amber()))
  val green: Light = Inr(Inr(Inl(Green())))

  val gen = Generic[Shape]
}
