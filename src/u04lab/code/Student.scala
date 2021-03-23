package u04lab.code

import Lists._
import u04lab.code.Lists.List.{Cons, Nil, append, contains, filter, map}

import scala.annotation.tailrec // import custom List type (not the one in Scala stdlib)

trait Student {
  def name: String
  def year: Int
  def enrolling(course: Course*): Unit // the student participates to a Course
  def courses: List[String] // names of course the student participates to
  def hasTeacher(teacher: String): Boolean // is the student participating to a course of this teacher?
}

trait Course {
  def name: String
  def teacher: String
}

case class StudentImpl(override val name: String, override val year: Int) extends Student {
  private var _courses: List[Course] = Nil()

  override def enrolling(courses: Course*): Unit = courses.map(Cons(_, Nil())).foreach(c => _courses = append(_courses, c))

  override def courses: List[String] = map(_courses)(_.name)

  override def hasTeacher(teacher: String): Boolean = contains(map(_courses)(_.teacher), teacher)
}

case class CourseImpl(override val name: String, override val teacher: String) extends Course

object Student {
  def apply(name: String, year: Int = 2017): Student = StudentImpl(name, year)
}

object Course {
  def apply(name: String, teacher: String): Course = CourseImpl(name, teacher)
}

object sameTeacher {
  def unapply(courses: List[Course]): scala.Option[String] = courses match {
    case Cons(h,t) => List.foldLeft(t)(scala.Option(h.teacher))((acc, course) => acc.filter(_ == course.teacher))
    case _ => scala.Option.empty
  }
  /* Optimized version which returns early
   = {
    @tailrec
    def inner(cs: List[Course], commonTeacher: String): Option[String] = cs match {
      case Cons(h,_) if h.teacher != commonTeacher => scala.Option.empty
      case Cons(h,t) => inner(t, commonTeacher)
      case Nil() => scala.Option(commonTeacher)
    }
    courses match {
      case Cons(h,t) => inner(t, h.teacher)
      case _ => scala.Option.empty
    }
  }
  */
}

object Try extends App {
  val cPPS = Course("PPS","Viroli")
  val cPCD = Course("PCD","Ricci")
  val cSDR = Course("SDR","D'Angelo")
  val s1 = Student("mario",2015)
  val s2 = Student("gino",2016)
  val s3 = Student("rino") //defaults to 2017
  s1.enrolling(cPPS)
  s1.enrolling(cPCD)
  s2.enrolling(cPPS)
  s3.enrolling(cPPS)
  s3.enrolling(cPCD)
  s3.enrolling(cSDR)
  println(s1.courses, s2.courses, s3.courses) // (Cons(PCD,Cons(PPS,Nil())),Cons(PPS,Nil()),Cons(SDR,Cons(PCD,Cons(PPS,Nil()))))
  println(s1.hasTeacher("Ricci")) // true

  val courses = List(Course("OOP","Viroli"), Course("PPS","Viroli"))
  courses match {
    case sameTeacher(t) => println(s"$courses have same teacher $t")
    case _ => println(s"$courses have different teachers ")
  }
}

/** Hints:
  * - simply implement Course, e.g. with a case class
  * - implement Student with a StudentImpl keeping a private Set of courses
  * - try to implement in StudentImpl method courses with map
  * - try to implement in StudentImpl method hasTeacher with map and find
  * - check that the two println above work correctly
  * - refactor the code so that method enrolling accepts a variable argument Course*
  */
