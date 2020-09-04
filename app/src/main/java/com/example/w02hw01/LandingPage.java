package com.example.w02hw01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LandingPage extends AppCompatActivity {
    public static final String USERNAME_EXTRA = "com.example.w02hw01_USERNAME";
    private TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        welcomeText = findViewById(R.id.welcomeText);

        Intent incoming = getIntent();
        welcomeText.setText("Welcome\n@" + incoming.getStringExtra(USERNAME_EXTRA));

    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, LandingPage.class);
        return intent;
    }
}