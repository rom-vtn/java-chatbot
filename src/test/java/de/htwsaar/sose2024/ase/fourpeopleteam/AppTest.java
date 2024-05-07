package de.htwsaar.sose2024.ase.fourpeopleteam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testAdder() {
        assertEquals(2, App.add(1, 1));
    }

    @Test
    public void testExceptionThrown() {
        assertThrows(NullPointerException.class, ()->{App.throwError();});
    }
}
