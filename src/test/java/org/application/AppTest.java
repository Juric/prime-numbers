package org.application;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.Array;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    final String[] incorrectPath = {"A:/DoesntExist"};
    final String[] correctPath = {"C:/Users/pjuri/Downloads/SpringProjects/prime-numbers/vzorek_dat.xlsx"};
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldPass() {
        Assertions.assertAll("actual prime numbers",
                () -> assertTrue(App.isPrime(7L)),
                () -> assertTrue(App.isPrime(13L)),
                () -> assertTrue(App.isPrime(5645657L)),
                () -> assertTrue(App.isPrime(9788677L)));
    }

    @Test
    public void shouldFail() {
        Assertions.assertAll("Non-prime numbers",
                () -> assertFalse(App.isPrime(6L)),
                () -> assertFalse(App.isPrime(10L)),
                () -> assertFalse(App.isPrime(15L)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void argsPathFailTest(){
        App.main(incorrectPath);
    }

    @Test
    public void argsPathSuccessTest(){
        App.main(correctPath);
    }
}
