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

        //We get the intent because we want to get the value that was passed in from the login page.
        //We do this to customize the welcome message.
        Intent incoming = getIntent();
        welcomeText.setText("Welcome\n@" + incoming.getStringExtra(USERNAME_EXTRA));

    }

    /**
     * Returns the intent to be able to move to this activity.
     * @param context The context of the activity we are traveling from.
     * @return Returns the created context.
     */
    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, LandingPage.class);
        return intent;
    }
}