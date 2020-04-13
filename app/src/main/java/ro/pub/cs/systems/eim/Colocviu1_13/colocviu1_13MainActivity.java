package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class colocviu1_13MainActivity extends AppCompatActivity {
    private Button northButton, southButton, eastButton, westButton, navigateButton;
    TextView textView;
    private String textToView;
    public int clickedButtons;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.north:
                    textView.setText(textView.getText().toString()+ "North, ");
                    clickedButtons++;
                    break;
                case R.id.south:
                    textView.setText(textView.getText().toString()+ "South, ");
                    clickedButtons++;
                    break;
                case R.id.west:
                    textView.setText(textView.getText().toString()+ "West, ");
                    clickedButtons++;
                    break;
                case R.id.east:
                    textView.setText(textView.getText().toString()+ "East, ");
                    clickedButtons++;
                    break;
                case R.id.navigate:
//                    textToView.concat(", East");
                    break;
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_main);

        northButton = (Button)findViewById(R.id.north);
        southButton = (Button)findViewById(R.id.south);
        eastButton = (Button)findViewById(R.id.east);
        westButton = (Button)findViewById(R.id.west);
        navigateButton = (Button)findViewById(R.id.navigate);
        textView = (TextView) findViewById(R.id.textView);

        northButton.setOnClickListener(buttonClickListener);
        southButton.setOnClickListener(buttonClickListener);
        eastButton.setOnClickListener(buttonClickListener);
        westButton.setOnClickListener(buttonClickListener);
        navigateButton.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("Buttons_pressed")) {
                clickedButtons = savedInstanceState.getInt("Buttons_pressed");
            } else {
                clickedButtons = 0;
            }
        } else {
            clickedButtons = 0;
        }
        Log.d("MainActivity", "Clicked buttons: "+ clickedButtons);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("Buttons_pressed", clickedButtons);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("Buttons_pressed")) {
            clickedButtons = savedInstanceState.getInt("Buttons_pressed");
        } else {
            clickedButtons = 0;
        }
    }
}
