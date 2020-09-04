package com.example.w02hw01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.widget.Toast;

import com.example.w02hw01.DB.UserDAO;
import com.example.w02hw01.DB.UserDatabase;

public class MainActivity extends AppCompatActivity {
    private UserDAO mUserDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserDAO = Room.databaseBuilder(this, UserDatabase.class, UserDatabase.USER_TABLE)
                .allowMainThreadQueries()
                .build()
                .getUserDao();

    }
}