package u04lab.code

import org.junit.jupiter.api.Assertions.{assertEquals, assertFalse, assertTrue}
import org.junit.jupiter.api.{Assertions, Test}
import u04lab.code.Lists.List.Cons
import u04lab.code.Lists.List.Nil
import u04lab.code.Lists.List

class StudentTest {
  @Test def testStudent() {
    assertEquals("mario", Student("mario").name)
    assertEquals(2000, Student("mario", 2000).year)
  }

  @Test def testStudentEnrolling() {
    val s1 = Student("mario",2015)
    s1.enrolling(Course("PPS","Viroli"), Course("PCD","Ricci"))
    assertEquals(Cons("PPS", Cons("PCD", Nil())), s1.courses)
    assertTrue(s1.hasTeacher("Ricci"))
    assertFalse(s1.hasTeacher("Unknown"))
  }

  @Test def testSameTeacher() {
    assertEquals(scala.Option("Viroli"), sameTeacher.unapply(List(Course("V1","Viroli"), Course("V2","Viroli"))))
    assertEquals(scala.Option.empty, sameTeacher.unapply(List(Course("V1","Viroli"), Course("V2","Viroli"), Course("R1","Ricci"))))
    assertEquals(scala.Option("Viroli"), sameTeacher.unapply(List(Course("V1","Viroli"))))
    assertEquals(scala.Option.empty, sameTeacher.unapply(List()))
  }
}
