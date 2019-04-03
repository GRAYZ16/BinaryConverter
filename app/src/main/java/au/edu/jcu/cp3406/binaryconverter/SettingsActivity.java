package au.edu.jcu.cp3406.binaryconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity
{
    public static final int SETTINGS_REQUEST = 755;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void onChoose(View view)
    {
        Button button = (Button) view;

        String dataType = button.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("dataType", dataType);
        setResult(RESULT_OK, intent);
        finish();

    }
}
