package u04lab.code

import org.junit.jupiter.api.Assertions.{assertEquals, assertFalse, assertTrue}
import org.junit.jupiter.api.Test
import u04lab.code.Lists.List.Cons
import u04lab.code.Lists.List.Nil

class StudentTest {
  @Test def testStudent() {
    assertEquals("mario", Student("mario").name)
    assertEquals(2000, Student("mario", 2000).year)
  }
  @Test def testStudentEnrolling() {
    val s1 = Student("mario",2015)
    val c1 = Course("PPS","Viroli")
    val c2 = Course("PCD","Ricci")
    s1.enrolling(c1, c2)
    assertEquals(Cons("PCD", Cons("PPS", Nil())), s1.courses)
    assertTrue(s1.hasTeacher("Ricci"))
    assertFalse(s1.hasTeacher("Unknown"))
  }
}
