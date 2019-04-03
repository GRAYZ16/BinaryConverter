package au.edu.jcu.cp3406.binaryconverter;

import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ConverterActivity extends AppCompatActivity
{
    private Converter converter;
    int dataType;

    private EditText inputText;
    private TextView tvInput;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        inputText = findViewById(R.id.etInput);
        tvInput = findViewById(R.id.tvInput);




        if(savedInstanceState == null)
        {
            dataType = Converter.BINARY;
            tvInput.setText(String.format(getResources().getString(R.string.tvSuffix), getResources().getString(R.string.binary)));
        }
        else
        {
            inputText.setText(savedInstanceState.getString("input"));
        }

        converter = new Converter("0", dataType);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("input", inputText.getText().toString());

    }

    public void onClear(View view)
    {
        EditText input = findViewById(R.id.etInput);
        input.setText("");
    }

    public void onConvert(View view)
    {
        EditText input = findViewById(R.id.etInput);

        TextView binText = findViewById(R.id.tvBinary);
        TextView hexText = findViewById(R.id.tvHexadecimal);
        TextView decText = findViewById(R.id.tvDecimal);

        converter.setValue(input.getText().toString(), dataType);

        binText.setText(converter.getBinaryRepresentation());
        hexText.setText(converter.getHexRepresentation());
        decText.setText(converter.getDecimalRepresenation());
    }

    public void onSettings(View view)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SettingsActivity.SETTINGS_REQUEST)
        {
            if(resultCode == RESULT_OK)
            {
                if(data != null)
                {
                    String type = data.getStringExtra("dataType");

                    switch (type)
                    {
                        case "Binary":
                            dataType = Converter.BINARY;
                            tvInput.setText(String.format(getResources().getString(R.string.tvSuffix), getResources().getString(R.string.binary)));
                            break;

                        case "Hexadecimal":
                            dataType = Converter.HEX;
                            tvInput.setText(String.format(getResources().getString(R.string.tvSuffix), getResources().getString(R.string.hexadecimal)));
                            break;

                        case "Decimal":
                            dataType = Converter.DECIMAL;
                            tvInput.setText(String.format(getResources().getString(R.string.tvSuffix), getResources().getString(R.string.decimal)));
                            break;

                        default:
                            dataType = Converter.BINARY;
                            tvInput.setText(String.format(getResources().getString(R.string.tvSuffix), getResources().getString(R.string.binary)));
                    }
                }
            }
        }
    }
}
