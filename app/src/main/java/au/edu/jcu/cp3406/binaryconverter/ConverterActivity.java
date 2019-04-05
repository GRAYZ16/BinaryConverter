package au.edu.jcu.cp3406.binaryconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConverterActivity extends AppCompatActivity
{
    private Converter converter;
    private int dataFormat; // Current Data Format of the input field

    private EditText inputText;
    private TextView tvInput;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        inputText = findViewById(R.id.etInput);
        tvInput = findViewById(R.id.tvInput);

        // If savedInstanceState exists, the app is probably returning from SettingsActivity
        if (savedInstanceState == null)
        {
            dataFormat = Converter.BINARY;
            tvInput.setText(String.format(getResources().getString(R.string.tvSuffix), getResources().getString(R.string.binary)));
        }
        else
        {
            inputText.setText(savedInstanceState.getString("input"));
        }

        converter = new Converter();
    }

    // Preserve input text when activity is going out of scope (SettingsActivity)
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("input", inputText.getText().toString());
    }

    // Clear the input text when button is clicked
    public void onClear(View view)
    {
        EditText input = findViewById(R.id.etInput);
        input.setText("");
    }

    // When convert is pressed, set the value of the conversion and display the answers
    public void onConvert(View view)
    {
        EditText input = findViewById(R.id.etInput);

        TextView binText = findViewById(R.id.tvBinary);
        TextView hexText = findViewById(R.id.tvHexadecimal);
        TextView decText = findViewById(R.id.tvDecimal);

        // When input is invalid, Converter throws NumberFormatException. Most appropriate point to
        // catch is at the Activity level.
        try
        {
            converter.setValue(input.getText().toString(), dataFormat);
        }
        catch (NumberFormatException e)
        {
            Toast.makeText(getApplicationContext(), getString(R.string.invalidInput), Toast.LENGTH_LONG).show();
        }

        binText.setText(converter.getBinaryFormat());
        hexText.setText(converter.getHexadecimalFormat());
        decText.setText(converter.getDecimalFormat());
    }

    // When settings button is pressed, Create an intent with the settings activity
    public void onSettings(View view)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }

    // If the SettingsActivity was successful, retrieve the new desired input format
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SettingsActivity.SETTINGS_REQUEST)
        {
            if (resultCode == RESULT_OK)
            {
                if (data != null)
                {
                    // Intent returns the new dataFormat
                    String type = data.getStringExtra("dataFormat");

                    // Settings activity returns strings from button text. Not the greatest solution
                    // if localisation is required. SettingsActivity should be modified if needed.
                    switch (type)
                    {
                        // Set the dataFormat and adjust the Activity as necessary

                        case "Binary":
                            dataFormat = Converter.BINARY;
                            tvInput.setText(String.format(getResources().getString(R.string.tvSuffix), getResources().getString(R.string.binary)));
                            break;

                        case "Hexadecimal":
                            dataFormat = Converter.HEX;
                            tvInput.setText(String.format(getResources().getString(R.string.tvSuffix), getResources().getString(R.string.hexadecimal)));
                            break;

                        case "Decimal":
                            dataFormat = Converter.DECIMAL;
                            tvInput.setText(String.format(getResources().getString(R.string.tvSuffix), getResources().getString(R.string.decimal)));
                            break;

                        default:
                            dataFormat = Converter.BINARY;
                            tvInput.setText(String.format(getResources().getString(R.string.tvSuffix), getResources().getString(R.string.binary)));
                    }
                }
            }
        }
    }
}
