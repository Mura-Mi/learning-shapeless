package yokohama.murataku

sealed trait Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
case class Leaf[A](value: A) extends Tree[A]

object Ch3Point4 extends App {
  import Ch3._

  val enc = CsvEncoder[Tree[Int]]
  println(
    writeCsv(
      List(
        Branch(Branch(Leaf(3), Branch(Leaf(3), Leaf(4))), Branch(Leaf(55), Leaf(225)))
      )
    )
  )
}
