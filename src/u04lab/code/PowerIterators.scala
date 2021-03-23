package u04lab.code

import Optionals._
import Lists._
import Streams._

trait PowerIterator[A] {
  def next(): Option[A]
  def allSoFar(): List[A]
  def reversed(): PowerIterator[A]
}

trait PowerIteratorsFactory {
  def incremental(start: Int, successive: Int => Int): PowerIterator[Int]
  def fromList[A](list: List[A]): PowerIterator[A]
  def randomBooleans(size: Int): PowerIterator[Boolean]
}

case class PowerIteratorImpl[A](
                               private var _future: Stream[A]
                               ) extends PowerIterator[A] {
  private var past: List[A] = List.nil

  override def next(): Option[A] = _future match {
      case Stream.Cons(h,t) =>
        _future = t()
        val v = h()
        past = List.append(past, List.Cons(v, List.Nil()))
        Option.of(v)
      case Stream.Empty() => Option.empty
  }

  override def allSoFar(): List[A] = past

  override def reversed(): PowerIterator[A] = new PowerIteratorsFactoryImpl().fromList(List.reverse(past))
}

class PowerIteratorsFactoryImpl extends PowerIteratorsFactory {

  override def incremental(start: Int, successive: Int => Int): PowerIterator[Int] =
    PowerIteratorImpl(Stream.iterate(start)(successive))

  override def fromList[A](list: List[A]): PowerIterator[A] = PowerIteratorImpl(
    Stream.map(
      Stream.takeWhile(
        Stream.iterate(list) {
          case List.Cons(_, t) => t
          case _ => List.nil
        }
      )(_ != List.nil)
    ) {
      case List.Cons(h, _) => h
    })

  override def randomBooleans(size: Int): PowerIterator[Boolean] =
    PowerIteratorImpl(Stream.take(Stream.generate(Math.random() < 0.5))(size))
}
