package com.example.w02hw01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.w02hw01.DB.User;
import com.example.w02hw01.DB.UserDAO;
import com.example.w02hw01.DB.UserDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

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

    /**
     * this function automatically passes in data into the db. We have some tester users to start
     * with.
     */
    private void autoPopulateDB() {
        User newUser = new User("din_djarin", "baby_yoda_ftw");
        User secondNew = new User("csumb", "otters_woo");
        User lastNew = new User("username", "password");

        mUserDAO.insert(newUser);
        mUserDAO.insert(secondNew);
        mUserDAO.insert(lastNew);
    }

    /**
     * this function connects are the member varaibles to their respective views. It also sets up
     * the buttons onClick listener.
     */
    private void connectMembers() {
        userText = findViewById(R.id.usernameEditText);
        passText = findViewById(R.id.editTextPassword);
        submitButton = findViewById(R.id.loginButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passedInUser = userText.getText().toString();
                String passedInPass = passText.getText().toString();

                //We clear the field colors to get rid of any red fields.
                resetFieldColors();
                //This function checks to see if the values typed in by the user are valid.
                checkValidLogin(passedInUser, passedInPass);
            }
        });
    }

    /**
     * This function makes calls to the db to see if the username and password passed in were valid
     * If they were, it starts the landing page activity. If they weren't, the the input with the
     * incorrect value will be highlighted and the user will see a little toast message telling them
     * what went wrong.
     * @param username the username the user typed
     * @param password the password the user typed
     */
    private void checkValidLogin(String username, String password) {
        //The check username will check to see if a password was returned. That means the user
        //exists. If a void is returned, then the user does not exist.
        String actualPassword = checkUsername(username);

        //If a null was passed back, then the username does not exist. We signal the user.
        if (actualPassword == null) {
            signalInvalidUser();
            return;
        }

        //We check to see if the password that was type is equal to the 1 we got from the DB. If
        //not we signal the user.
        if (!checkPassword(password, actualPassword)){
            signalInvalidPassword();
            return;
        }

        //If everything worked, we go to the landing page.
        goToLandingPage();
    }

    /**
     * We check to see if the username that was passed in was in our DB. If so, we return the
     * password. If not, we return null.
     * @param username The username the user typed in
     * @return the password related to the username
     */
    public String checkUsername(String username) {
        User user = mUserDAO.getUsersFromUsername(username);

        if(user == null) {
            return null;
        }

        return user.getPassword();
    }

    /**
     * This function checks to see if the password that was typed in is the same password we have in
     * the DB
     * @param password
     * @param actualPassword
     * @return boolean specifying if the two passwords match
     */
    public boolean checkPassword(String password, String actualPassword) {
        return password.equals(actualPassword);
    }

    /**
     * If the username was invalid, we will highlight the field with red and display a message
     * telling the user that the username was not found in the DB.
     */
    private void signalInvalidUser() {
        userText.setBackgroundColor(ContextCompat.getColor(this, R.color.errorBackground));
        Toast.makeText(this, "The username was incorrect!", Toast.LENGTH_SHORT).show();
    }

    /**
     * If the password was invalid, we will highlight the password field with red and display
     * a message telling the user the password was incorrect.
     */
    private void signalInvalidPassword() {
        passText.setBackgroundColor(ContextCompat.getColor(this, R.color.errorBackground));
        Toast.makeText(this, "The password was incorrect!", Toast.LENGTH_SHORT).show();
    }

    /**
     * If everything was valid, then we will send the user to the landing page. We get the intent
     * form the LandingPage activity. We add the username as an extra and start the new activity.
     */
    private void goToLandingPage() {
        Intent intent = LandingPage.getIntent(MainActivity.this);
        intent.putExtra(LandingPage.USERNAME_EXTRA, userText.getText().toString());
        startActivity(intent);
    }

    /**
     * This function resets the colors of the input fields after the button is pressed. This is so
     * the input fields are no longer are highlighted after the user passes in new inputs.
     */
    private void resetFieldColors() {
        userText.setBackgroundColor(ContextCompat.getColor(this, R.color.cleanBackground));
        passText.setBackgroundColor(ContextCompat.getColor(this, R.color.cleanBackground));
    }
}