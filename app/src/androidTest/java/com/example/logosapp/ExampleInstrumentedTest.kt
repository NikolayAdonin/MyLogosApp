package com.example.logosapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    public var activityActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickLoginButton(){
        Espresso.onView(ViewMatchers.withId(R.id.buttonLogin)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.buttonEnter)).perform(click())
    }

    @Test
    fun clickNavbarKab(){
        Espresso.onView(ViewMatchers.withId(R.id.buttonLogin)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.buttonEnter)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.ic_user_kab)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.ic_user_kab)).perform(click()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun clickNavbarPerson(){
        Espresso.onView(ViewMatchers.withId(R.id.buttonLogin)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.buttonEnter)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.ic_people)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.ic_people)).perform(click()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun clickNavbarNavigation(){
        Espresso.onView(ViewMatchers.withId(R.id.buttonLogin)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.buttonEnter)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.ic_navigation)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.ic_navigation)).perform(click()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}