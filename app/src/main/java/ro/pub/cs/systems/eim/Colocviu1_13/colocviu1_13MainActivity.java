package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class colocviu1_13MainActivity extends AppCompatActivity {
    private Button northButton, southButton, eastButton, westButton, navigateButton;
    TextView textView;
    private String textToView;
    public int clickedButtons;
    private IntentFilter intentFilter = new IntentFilter();

    private int serviceStatus = 12;
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
                    Intent intent = new Intent(getApplicationContext(), colocviu1_13SecondaryActivity.class);
                    String instructions = textView.getText().toString();
                    intent.putExtra("instructions", instructions);
                    textView.setText("");
                    clickedButtons = 0;
                    startActivityForResult(intent, 69);
                    break;
            }

            String instrs = textView.getText().toString();
//            if ( instrs.contains("North") && instrs.contains("South")
//                    && instrs.contains("East")  && instrs.contains("West")
            if (clickedButtons == 4 && serviceStatus == 12) {
                Log.d("MainActivity", "Sunt in if!!");
                Intent intent = new Intent(getApplicationContext(), Colocviu1_13Service.class);
                intent.putExtra("instructionsForService", instrs);
                getApplicationContext().startService(intent);
                serviceStatus = 10;
            }

        }
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("MainActivity", intent.getStringExtra("message"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_main);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("Buttons_pressed")) {
                clickedButtons = savedInstanceState.getInt("Buttons_pressed");
            } else {
                clickedButtons = 0;
            }
        } else {
            clickedButtons = 0;
        }

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

        Log.d("MainActivity", "Clicked buttons: "+ clickedButtons);
        intentFilter.addAction("ro.pub.cs.systems.eim.practicaltest01.sendMessage");
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

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, Colocviu1_13Service.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 69) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
}
