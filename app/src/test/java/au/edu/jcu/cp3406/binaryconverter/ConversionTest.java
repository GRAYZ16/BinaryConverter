package au.edu.jcu.cp3406.binaryconverter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Conversion Testing for Converter class
 */
public class ConversionTest
{
    private Converter converter = new Converter();

    // Ensuring all hex functionality is correct. Tests both bin->hex and dec->hex conversions.
    @Test
    public void hexToBinaryTest()
    {
        String hex = "123456789ABCDEF";
        converter.setValue(hex, Converter.HEX);
        assertEquals(hex, converter.getHexadecimalFormat());
    }

    // Ensuring all binary functionality is correct. Tests the structure of the class.
    @Test
    public void binaryTest()
    {
        String bin = "1011011010";
        converter.setValue(bin, Converter.BINARY);
        assertEquals(bin, converter.getBinaryFormat());
    }

    // Ensuring all decimal functionality is correct. Tests both bin->dec and dec->bin conversions.
    @Test
    public void decimalToBinaryTest()
    {
        String decimal = "12345678987654321";
        converter.setValue(decimal, Converter.DECIMAL);
        assertEquals(decimal, converter.getDecimalFormat());
    }

    // Testing that an exception is thrown when input is invalid. Invalid input intentionally left
    // to the Activity to solve
    @Test(expected = NumberFormatException.class)
    public void exceptionTest()
    {
        String invalidInput = "z";
        converter.setValue(invalidInput, Converter.BINARY);
    }
}