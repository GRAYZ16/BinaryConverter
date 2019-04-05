package au.edu.jcu.cp3406.binaryconverter;

public class Converter
{
    // Accepted Data Formats
    static final int BINARY = 0;
    static final int HEX = 1;
    static final int DECIMAL = 2;

    // Storing the conversion  value as a binary string
    private String binaryRepresentation;

    // Default constructor assumes a value of 0 in binary form
    public Converter()
    {
        this("0", BINARY);
    }

    public Converter(String value, int numberFormat)
    {
        setValue(value, numberFormat);
    }

    // Set the value of the converter to a new number with a given numberFormat
    void setValue(String value, int numberFormat)
    {
        // Convert value to storage base (binary)
        switch (numberFormat)
        {
            case BINARY:
                convertFromBinary(value);
                break;

            case HEX:
                convertFromHex(value);
                break;

            case DECIMAL:
                convertFromDecimal(value);
                break;
        }
    }

    /*
     *  This code block is added to ensure any formatting errors are caught when executing the
     *  setValue function. As default Long.parseLong throws NumberFormatException to the class that
     *  executes setValue when String value does not match the formatting of numberFormat.
     *  Functionally, this code block is redundant but streamlines the invalid format detection.
     */
    private void convertFromBinary(String value)
    {
        Long input = Long.parseLong(value, 2);
        binaryRepresentation = Long.toBinaryString(input);
    }

    private void convertFromHex(String value)
    {
        long input = Long.parseLong(value, 16);
        binaryRepresentation = Long.toBinaryString(input);
    }

    private void convertFromDecimal(String value)
    {
        long input = Long.parseLong(value);
        binaryRepresentation = Long.toBinaryString(input);

    }

    String getBinaryFormat()
    {
        return binaryRepresentation;
    }

    String getHexadecimalFormat()
    {
        long value = Long.parseLong(binaryRepresentation, 2);
        return Long.toHexString(value).toUpperCase();
    }

    String getDecimalFormat()
    {
        long value = Long.parseLong(binaryRepresentation, 2);
        return Long.toString(value);
    }

}
