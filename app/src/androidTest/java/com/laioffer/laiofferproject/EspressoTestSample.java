
package com.laioffer.laiofferproject;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ResourceBundle;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)
@LargeTest

public class EspressoTestSample {
    private String mStringToBetyped;

    @Rule
    public ActivityTestRule<ControlPanel> mActivityRule = new ActivityTestRule<>(
            ControlPanel.class);

    @Before
    public void initValidString() {
        mStringToBetyped = "Espresso";
    }

    @Test
    public void checkYelp() {
        // Press button
        onView(withText("Yelp")).perform(click()).check(matches(withText(mStringToBetyped)));
    }

    @Test
    public void checkBackend() {
        // Press button
        onView(withId(R.id.button_backend)).perform(click()).check(matches(withText("New text")));
    }


}