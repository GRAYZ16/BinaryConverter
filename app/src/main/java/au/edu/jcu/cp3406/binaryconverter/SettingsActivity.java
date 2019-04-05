package au.edu.jcu.cp3406.binaryconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity
{
    public static final int SETTINGS_REQUEST = 755;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    // When any button is clicked, return intent with dataFormat chosen
    public void onChoose(View view)
    {
        Button button = (Button) view;

        String dataFormat = button.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("dataFormat", dataFormat);
        setResult(RESULT_OK, intent);
        finish();

    }
}
