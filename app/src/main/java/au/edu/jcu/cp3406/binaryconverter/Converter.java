package au.edu.jcu.cp3406.binaryconverter;

public class Converter
{
    public static final int BINARY = 0;
    public static final int HEX = 1;
    public static final int DECIMAL = 2;

    private String binaryRepresentation;

    public Converter()
    {
        this("0", BINARY);
    }

    public Converter(String value, int dataType)
    {
        setValue(value, dataType);
    }

    public void setValue(String value, int dataType)
    {

        // Convert value to storage base (binary)
        switch(dataType)
        {
            case BINARY:
                binaryRepresentation = value;
                break;

            case HEX:
                convertFromHex(value);
                break;

            case DECIMAL:
                convertFromDecimal(value);
        }
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

    public String getBinaryRepresentation()
    {
        return binaryRepresentation;
    }

    public String getHexRepresentation()
    {
        long value = Long.parseLong(binaryRepresentation, 2);
        return Long.toHexString(value).toUpperCase();
    }

    public String getDecimalRepresenation()
    {
        long value = Long.parseLong(binaryRepresentation, 2);
        return Long.toString(value);
    }

}
