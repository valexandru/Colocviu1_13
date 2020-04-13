package ro.pub.cs.systems.eim.Colocviu1_13;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class colocviu1_13SecondaryActivity extends AppCompatActivity {

    private TextView instructionsTextView;
    private Button okButton, cancelButton;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.register:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_secondary);

        instructionsTextView = (TextView)findViewById(R.id.textView2);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("instructions")) {
            String instructions = intent.getStringExtra("instructions");
            instructionsTextView.setText(instructions);
        }

        okButton = (Button)findViewById(R.id.register);
        okButton.setOnClickListener(buttonClickListener);
        cancelButton = (Button)findViewById(R.id.cancel);
        cancelButton.setOnClickListener(buttonClickListener);
    }

}
