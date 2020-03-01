package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView messageText, scorerHomeText,scorerAwayText, resultText;
    private String result, message, scorerHome,scorerAway;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultText =findViewById(R.id.textView1);
        messageText = findViewById(R.id.textView2);
        scorerHomeText = findViewById(R.id.textView3);
        scorerAwayText = findViewById(R.id.textView4);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            result = bundle.getString("result");
            message = bundle.getString("messages");
            scorerHome = bundle.getString("scorerHome");
            scorerAway = bundle.getString("scorerAway");
            scorerHomeText.setText(scorerHome);
            scorerAwayText.setText(scorerAway);
            messageText.setText(message);
            resultText.setText(result);
        }
    }
}
