package u04lab.code

import org.junit.jupiter.api.Assertions.{assertEquals, assertFalse, assertTrue}
import org.junit.jupiter.api.Test
import u04lab.code.Lists.List._
import u04lab.code.Lists.List

class ListTest {
  @Test def testContains() {
    assertTrue(contains(Cons("test1", Cons("test2", Nil())), "test2"))
    assertFalse(contains(Cons("test1", Cons("test2", Nil())), "somethingelse"))
  }
  @Test def testApply() {
    assertEquals(Cons("test1", Cons("test2", Nil())), List("test1", "test2"))
    assertEquals(Cons("test1", Nil()), List("test1"))
    assertEquals(Nil(), List())
  }
}
