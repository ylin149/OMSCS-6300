package edu.gatech.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Junit test class created for use in Georgia Tech CS6300.
 *
 * You should implement your tests in this class.
 */

public class MyCustomStringTest {

    private MyCustomStringInterface mycustomstring;

    @Before
    public void setUp() {
        mycustomstring = new MyCustomString();
    }

    @After
    public void tearDown() {
        mycustomstring = null;
    }

    @Test
    // Description: Instructor-provided test
    public void testCountStringsS1() {
        mycustomstring.setString("My numbers are 11, 96, and thirteen");
        assertEquals(5, mycustomstring.countStrings());
    }

    @Test(expected = NullPointerException.class)
    // Description: Asserts if CountStrings throws an exception when the string is null
    public void testCountStringsS2() {
        mycustomstring.setString(null);
        mycustomstring.countStrings();
    }

    @Test
    // Description: Asserts if the provided string is empty
    public void testCountStringsS3() {
        mycustomstring.setString("");
        assertEquals(mycustomstring.countStrings(), 0);
    }

    @Test
    // Description: Tests complex string containing numbers and letters
    public void testCountStringsS4() {
        mycustomstring.setString("CS6300 1s awes0m3");
        assertEquals(mycustomstring.countStrings(), 4);
    }

    @Test
    // Description: Instructor-provided test
    public void testMultiplyNumberS1() {
        mycustomstring.setString("hello 90, bye 2");
        assertEquals("hello 180, bye 4", mycustomstring.multiplyNumber(2, false));
    }

    @Test(expected = NullPointerException.class)
    // Description: Asserts if MultiplyNumbers throws and NullPointerException if the input is null
    public void testMultiplyNumberS2() {
        mycustomstring.setString(null);
        mycustomstring.multiplyNumber(2,false);
    }

    @Test(expected = IllegalArgumentException.class)
    // Description: Assert if MultiplyNumbers throw an Exception when incorrect parameter n is passed
    public void testMultiplyNumberS3() {
        mycustomstring.setString("3Ls0");
        mycustomstring.multiplyNumber(-10, true);
    }

    @Test
    // Description: Tests when a negative number is passed
    public void testMultiplyNumberS4() {
        mycustomstring.setString("-99");
        assertEquals(mycustomstring.multiplyNumber(11, false), "-1089");
    }

    @Test
    // Description: Tests results for large multiplications and reverse enabled
    public void testMultiplyNumberS5() {
        mycustomstring.setString("99999");
        assertEquals(mycustomstring.multiplyNumber(1000, true), "00099999");
    }

    @Test
    // Description: Tests numbers mixed with letters and reverse enabled
    public void testMultiplyNumber6() {
        mycustomstring.setString("CS6300 1s awesom3");
        assertEquals(mycustomstring.multiplyNumber(5, true), "CS00513 5s awesom51");
    }

    @Test
    // Description: Instructor-provided test
    public void testConvertDigitsToBinaryInSubstringS1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToBinaryInSubstring(17, 23);
        assertEquals("I'd b3tt3r put s0000me d000101101ts in this 5tr1n6, right?", mycustomstring.getString());
    }

    @Test(expected = NullPointerException.class)
    // Description: Checks if the DigitsToBinaryInSubstring  throws a NullPointerException when the supplied string is null
    public void testConvertDigitsToBinaryInSubstringS2() {
        mycustomstring.setString(null);
        mycustomstring.convertDigitsToBinaryInSubstring(1,3);
    }

    @Test(expected = IllegalArgumentException.class)
    // Description: Checks if the DigitsToBinaryInSubstring  throws an IllegalArgumentException when the indexes are incorrect
    public void testConvertDigitsToBinaryInSubstringS3() {
        mycustomstring.setString("123456789");
        mycustomstring.convertDigitsToBinaryInSubstring(0, 20);
    }

    @Test(expected = MyIndexOutOfBoundsException.class)
    // Description: Checks if the DigitsToBinaryInSubstring  throws an MyIndexOutOfBoundsException when the endPosition is bigger then the length of the string
    public void testConvertDigitsToBinaryInSubstringS4() {
        mycustomstring.setString("12345");
        mycustomstring.convertDigitsToBinaryInSubstring(1, 20);
    }

    @Test
    // Description: Tests all numbers and checks if the numbers are correctly converted into binary
    public void testConvertDigitsToBinaryInSubstringS5() {
        mycustomstring.setString("1234");
        mycustomstring.convertDigitsToBinaryInSubstring(1, 4);
        assertEquals(mycustomstring.getString(),  "0001001000110100");
    }

    @Test
    // Description: Tests numbers mixed with letters
    public void testConvertDigitsToBinaryInSubstringS6() {
        mycustomstring.setString("a1 b2 c3 d4");
        mycustomstring.convertDigitsToBinaryInSubstring(1, 11);
        assertEquals(mycustomstring.getString(),  "a0001 b0010 c0011 d0100");
    }
}

