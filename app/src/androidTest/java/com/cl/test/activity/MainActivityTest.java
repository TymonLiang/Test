package com.cl.test.activity;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cl.test.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by c.l on 19/08/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    /**
     * test spinner
     * @throws Exception
     */
    @Test
    public void spinnerTest() throws Exception {
        onView(withId(R.id.spinner_transport)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("taronga zoo"))).perform(click());
        onView(withId(R.id.spinner_transport)).check(
                matches(withSpinnerText(containsString("taronga zoo"))));
    }

    /**
     * test navigation button
     * @throws Exception
     */
    @Test
    public void onNavClick() throws Exception {
        onView(withText("Navigate")).perform(click());
        pressBack();
    }

    /**
     * test next button
     * @throws Exception
     */
    @Test
    public void onNextClick() throws Exception {
        onView(withText("Go to Test1")).perform(click());
        pressBack();
    }

}