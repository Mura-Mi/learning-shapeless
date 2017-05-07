package yokohama.murataku

import shapeless._
import yokohama.murataku._

object Ch3 extends App {

  val e: CsvEncoder[String :: Int :: Boolean :: HNil] = implicitly

  // println(e.encode("hoge" :: 150 :: true :: HNil))

  println ( writeCsv(List(
    IceCream("vanilla", 15, true),
    IceCream("choco", 0, true),
    IceCream("berry", 5, false)
  )) )

  def separate: Unit = println("-----")
  separate

  println( writeCsv(List(
    Employee("vanilla", 15, true),
    Employee("choco", 0, true),
    Employee("berry", 5, false),
    Employee("You Harada", 42, false)
  )) )

  val shapes: List[Shape] = List(
    Rectangle(7, 24),
    Circle(25)
  )

  separate
  println( writeCsv(shapes) )

}

