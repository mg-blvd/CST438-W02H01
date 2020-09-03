package com.example.w02hw01;

import android.content.Intent;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    MainActivity exampleMain;
    LandingPage exampleLanding;

    @Before
    public void setup() {
        exampleMain = new MainActivity();
        exampleLanding = new LandingPage();
    }

    @Test
    public void checkWrongPassword() {
        //When we test the password, we do not get it from the DB. We've already gotten it. Here we
        //just make sure that the function is returning false when the passwords don't match.
        String wrongPass = "thisPasswordIsWrong";
        String rightPass = "thisPasswordIsRight";

        assertFalse(exampleMain.checkPassword(wrongPass, rightPass));
    }

    @Test
    public void checkRightPassword() {
        //When we test the password, we do not get it from the DB. We've already gotten it. Here we
        //just make sure that the function is returning true when the passwords match.
        String origPass = "thisPasswordIsCorrect";
        String actualPass = origPass;

        assertTrue(exampleMain.checkPassword(origPass, actualPass));
    }

    @Test
    public void checkIntentWorks() {
        //If the intent is not null, then we know we've received an intent from the landing page.
        Intent  intent = exampleLanding.getIntent(exampleMain);
        assertNotNull(intent);

    }
}