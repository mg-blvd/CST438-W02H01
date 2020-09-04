package com.example.w02hw01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.w02hw01.DB.User;
import com.example.w02hw01.DB.UserDAO;
import com.example.w02hw01.DB.UserDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserDAO mUserDAO;
    private EditText userText;
    private EditText passText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserDAO = Room.databaseBuilder(this, UserDatabase.class, UserDatabase.USER_TABLE)
                .allowMainThreadQueries()
                .build()
                .getUserDao();

        autoPopulateDB();
        connectMembers();

    }

    private void autoPopulateDB() {
        User newUser = new User("din_djarin", "baby_yoda_ftw");
        User secondNew = new User("csumb", "otters_woo");
        User lastNew = new User("username", "password");

        mUserDAO.insert(newUser);
        mUserDAO.insert(secondNew);
        mUserDAO.insert(lastNew);
    }

    private void connectMembers() {
        userText = findViewById(R.id.usernameEditText);
        passText = findViewById(R.id.editTextPassword);
        submitButton = findViewById(R.id.loginButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passedInUser = userText.getText().toString();
                String passedInPass = userText.getText().toString();

                checkValidLogin(passedInUser, passedInPass);
            }
        });
    }

    private void checkValidLogin(String username, String password) {
        String actualPassword = checkUsername(username);

        if (actualPassword == null) {
            signalInvalidUser();
            return;
        }

        if (!checkPassword(password, actualPassword)){
            signalInvalidPassword();
            return;
        }


    }

    private String checkUsername(String username) {
        User user = mUserDAO.getUsersFromUsername(username);
        if(user == null) {
            return null;
        }

        return user.getPassword();
    }

    private boolean checkPassword(String password, String actualPassword) {
        return password.equals(actualPassword);
    }

    private void signalInvalidUser() {
        userText.setBackgroundColor(ContextCompat.getColor(this, R.color.errorBackground));
        Toast.makeText(this, "The username was incorrect!", Toast.LENGTH_SHORT).show();
    }

    private void signalInvalidPassword() {
        passText.setBackgroundColor(ContextCompat.getColor(this, R.color.errorBackground));
        Toast.makeText(this, "The password was incorrect!", Toast.LENGTH_SHORT).show();
    }
}