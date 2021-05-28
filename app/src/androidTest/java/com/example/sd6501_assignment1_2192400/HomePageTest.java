package com.example.sd6501_assignment1_2192400;

import android.view.View;

import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.regex.Pattern;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class HomePageTest {

    @Rule
    public ActivityTestRule<HomePage> HomePageActivityTestRule=new ActivityTestRule<>(HomePage.class);
    HomePage activity1;

    @Before
    public void setUp() throws Exception {
        activity1=HomePageActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        activity1=null;
    }

    @Test
    public void isActivityInView() {
        onView(withId(R.id.main)).check(ViewAssertions.matches(isDisplayed()));

    }

    @Test
    public void test_visibility_login_button() {
        onView(withId(R.id.btnLogin)).check(ViewAssertions.matches(isDisplayed()));
    }


    @Test
    public void test_visibility_register_button() {
        onView(withId(R.id.btnRegisterMain)).check(ViewAssertions.matches(isDisplayed()));
    }

}

