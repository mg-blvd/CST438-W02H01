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
        String wrongPass = "thisPasswordIsWrong";
        String rightPass = "thisPasswordIsRight";

        assertFalse(exampleMain.checkPassword(wrongPass, rightPass));
    }

    @Test
    public void checkRightPassword() {
        String origPass = "thisPasswordIsCorrect";
        String actualPass = origPass;

        assertTrue(exampleMain.checkPassword(origPass, actualPass));
    }

    @Test
    public void checkIntentWorks() {
        Intent  intent = exampleLanding.getIntent(exampleMain);
        assertNotNull(intent);

    }
}