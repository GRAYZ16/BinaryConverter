package au.edu.jcu.cp3406.binaryconverter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Conversion Testing for Converter class
 */
public class ConversionTest
{
    private Converter converter = new Converter();

    @Test
    public void hexToBinaryTest()
    {
        String hex = "123456789ABCDEF";
        converter.setValue(hex, Converter.HEX);
        assertEquals(hex, converter.getHexRepresentation());
    }

    @Test
    public void binaryTest()
    {
        String bin = "1011011010";
        converter.setValue(bin, Converter.BINARY);
        assertEquals(bin, converter.getBinaryRepresentation());
    }

    @Test
    public void decimalToBinaryTest()
    {
        String decimal = "12345678987654321";
        converter.setValue(decimal, Converter.DECIMAL);
        assertEquals(decimal, converter.getDecimalRepresenation());
    }
}