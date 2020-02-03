package com.yassou.postpartumapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.yassou.postpartumapp.Activities.WelcomeScreenActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)


class WelcomeScreenTest {

    @get:Rule
    val activityRule = ActivityTestRule(WelcomeScreenActivity::class.java, false, false)

    @Test
    fun appLaunchesSuccessfully() {
        activityRule.launchActivity(null)
        (WelcomeScreenActivity::class.java)
    }


    @Test
    fun onLaunchTestButtonIsDisplayed() {
        ActivityScenario.launch(WelcomeScreenActivity::class.java)
        onView(withId(R.id.take_a_test))
            .check(matches(isDisplayed()))
            .perform(click())

    }

    @Test
    fun onLaunchWhatIsPpdTextViewIsDisplayed() {
        ActivityScenario.launch(WelcomeScreenActivity::class.java)
        onView(withId(R.id.what_is_PPD))
            .check(matches(isDisplayed()))
            .perform(click())
    }


}


