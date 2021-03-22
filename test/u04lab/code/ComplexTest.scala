package u04lab.code

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ComplexTest {
  @Test def testEquals {
    assertEquals(Complex(18.5,-21.3), Complex(18.5,-21.3))
  }
  @Test def testSum {
    assertEquals(Complex(18.0,21.0), Complex(10,20) + Complex(1,1) + Complex(7,0))
  }
  @Test def testMultiplication {
    assertEquals(Complex(-10.0,30.0), Complex(10,20) * Complex(1,1))
  }
}
