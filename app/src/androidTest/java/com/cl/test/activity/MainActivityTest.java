package com.cl.test.activity;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by c.l on 19/08/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void spinnerItemSelector() throws Exception {

    }

    @Test
    public void onNavClick() throws Exception {
        onView(withText("Navigate")).perform(click());
    }

    @Test
    public void onNextClick() throws Exception {
        onView(withText("Next")).perform(click());
    }

}