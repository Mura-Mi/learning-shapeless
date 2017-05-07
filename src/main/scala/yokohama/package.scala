package yokohama

import shapeless._

package object murataku {
  implicit val booleanEncoder: CsvEncoder[Boolean] = b => List(if(b) "yes" else "no")
  implicit val intEncoder: CsvEncoder[Int] = i=> List(i.toString)
  implicit val strEncoder: CsvEncoder[String] = a=> List(a)
  implicit val doubleEncoder: CsvEncoder[Double] = d=>List(d.toString)

  def createEncoder[A](func: A=>List[String]): CsvEncoder[A] = a => func(a)

  implicit val hnilEncoder: CsvEncoder[HNil] = nil => Nil

  implicit def hlistEncoder[H, T <: HList](
    implicit he: Lazy[CsvEncoder[H]], te: CsvEncoder[T]
  ): CsvEncoder[H :: T] = createEncoder {
    case h :: t => he.value.encode(h) ++ te.encode(t)
  }

  implicit val cnilEncoder: CsvEncoder[CNil] =
    createEncoder(any => throw new Exception("Inconceivable!"))

  implicit def coproductEncoder[H, T <: Coproduct](
    implicit
    hEncoder: Lazy[CsvEncoder[H]],
    tEncoder: CsvEncoder[T]
  ): CsvEncoder[H :+: T] = createEncoder {
    case Inl(h) => hEncoder.value.encode(h)
    case Inr(t) => tEncoder.encode(t)
  }
  
  def writeCsv[A](values: List[A])(implicit enc: CsvEncoder[A]): String = 
    values.map(v => enc(v).mkString(",")).mkString("\n")

  implicit def genericEncoder[A, R](
    implicit gen: Generic.Aux[A,R],
    enc: Lazy[CsvEncoder[R]]
  ): CsvEncoder[A] =
    createEncoder(a => enc.value.encode(gen.to(a)))

}
