package com.example.w02hw01;

import android.app.Application;
import android.content.Context;
import android.service.autofill.UserData;

import com.example.w02hw01.DB.User;
import com.example.w02hw01.DB.UserDAO;
import com.example.w02hw01.DB.UserDatabase;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private UserDAO mUserDAO;
    private UserDatabase mUserDatabase;
    @Before
    public void prepTests() {
        Context context = ApplicationProvider.getApplicationContext();

        mUserDatabase = Room.inMemoryDatabaseBuilder(context, UserDatabase.class).build();
        mUserDAO = mUserDatabase.getUserDao();

        User newUser = new User("din_djarin", "baby_yoda_ftw");
        User secondNew = new User("csumb", "otters_woo");
        User lastNew = new User("username", "password");

        mUserDAO.insert(newUser);
        mUserDAO.insert(secondNew);
        mUserDAO.insert(lastNew);
    }

    @After
    public void closeDB() {
        mUserDatabase.close();
    }

    @Test
    public void testCheckUsername() {
        //This function tests the db calls we make when checking the username
        //The function is supposed to return the users password

        String getPass = mUserDAO.getPasswordFromUsername("csumb");
        assertEquals("otters_woo", getPass);
    }

}