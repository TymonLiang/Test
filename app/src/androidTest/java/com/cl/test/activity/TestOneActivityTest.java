package com.cl.test.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cl.test.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by c.l on 19/08/2017.
 */
@RunWith(AndroidJUnit4.class)
public class TestOneActivityTest {

    @Rule
    public ActivityTestRule<TestOneActivity> mActivityRule = new ActivityTestRule<>(
            TestOneActivity.class);

    /**
     * viewpager test
     * @throws Exception
     */
    @Test
    public void testViewPagerUI() throws Exception {
        onView(withId(R.id.vp_top)).perform(swipeRight());
        onView(withId(R.id.vp_top)).perform(swipeLeft());
    }

    /**
     * header button test
     * @throws Exception
     */
    @Test
    public void onHeaderClick() throws Exception {
        onView(withText("Item1")).perform(click());
    }

    /**
     * change background test
     * @throws Exception
     */
    @Test
    public void onBackgroundTest() throws Exception {
        onView(withText("Red")).perform(click());
        onView(withText("Blue")).perform(click());
        onView(withText("Green")).perform(click());
    }

}