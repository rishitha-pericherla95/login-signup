package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    private TextView myText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Intent w = getIntent();
        //prints the welcome message with appropriate username
        String message = "Welcome " + w.getStringExtra("usertext") + "!";
        myText = findViewById(R.id.txtMessage);
        myText.setText(message);

    }
}
