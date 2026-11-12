package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for Maven GitHub Demo Application.
 */
public class AppTest {

    private App app;

    @BeforeEach
    public void setUp() {
        app = new App();
    }

    @Test
    @DisplayName("Test application greeting message")
    public void testGetGreeting() {
        String greeting = app.getGreeting();
        assertNotNull(greeting, "Greeting should not be null");
        assertTrue(greeting.contains("Maven GitHub Demo App"), "Greeting should contain application name");
        assertTrue(greeting.contains("1.0-SNAPSHOT"), "Greeting should contain application version");
    }

    @Test
    @DisplayName("Test addition method")
    public void testAdd() {
        assertEquals(12, app.add(5, 7), "5 + 7 should equal 12");
        assertEquals(0, app.add(-3, 3), "-3 + 3 should equal 0");
        assertEquals(-10, app.add(-4, -6), "-4 + -6 should equal -10");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4, 10, -2, -100})
    @DisplayName("Test isEven with even numbers")
    public void testIsEvenWithEvenNumbers(int number) {
        assertTrue(app.isEven(number), number + " should be even");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 11, -1, -99})
    @DisplayName("Test isEven with odd numbers")
    public void testIsEvenWithOddNumbers(int number) {
        assertFalse(app.isEven(number), number + " should be odd");
    }

    @Test
    @DisplayName("Test main method execution without exception")
    public void testMain() {
        assertDoesNotThrow(() -> App.main(new String[]{}), "Main method should execute without throwing exceptions");
    }
}
