// Remove the package main.java; line entirely from the top!

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void testAdd() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void testDivide_Success() {
        assertEquals(2.5f, calculator.divide(5.0f, 2.0f));
    }

    @Test
    void testDivide_ByZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(5.0f, 0.0f);
        });
        assertEquals("Cannot divide by zero", exception.getMessage());
    }
}
