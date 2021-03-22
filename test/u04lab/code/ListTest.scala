package u04lab.code

import org.junit.jupiter.api.Assertions.{assertFalse, assertTrue}
import org.junit.jupiter.api.Test
import u04lab.code.Lists.List._

class ListTest {
  @Test def testContains() {
    assertTrue(contains(Cons("test1", Cons("test2", Nil()))), "test2")
    assertFalse(contains(Cons("test1", Cons("test2", Nil()))), "somethingelse")
  }
}
