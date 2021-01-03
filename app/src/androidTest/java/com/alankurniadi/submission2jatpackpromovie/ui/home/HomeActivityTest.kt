package com.alankurniadi.submission2jatpackpromovie.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.alankurniadi.submission2jatpackpromovie.R
import com.alankurniadi.submission2jatpackpromovie.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadTrending() {
        //List
        onView(withId(R.id.rv_this_week)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_this_week)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        //Detail
        onView(withId(R.id.img_start)).check(matches(isDisplayed()))
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(withId(R.id.img_start)).check(matches(isDisplayed()))
    }

    @Test
    fun loadlMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.tv_title_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(withId(R.id.detail_activity_movie)).perform(swipeUp())
        onView(withId(R.id.detail_activity_movie)).perform(swipeDown())

        //onView(withId(R.id.detail_activity_movie)).perform(pressBack())
    }

    @Test
    fun loadTvShowList() {
        onView(withId(R.id.activity_main)).perform(swipeUp())
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.tv_title_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(withId(R.id.detail_activity_tv)).perform(swipeUp())
        onView(withId(R.id.detail_activity_tv)).perform(swipeDown())

        //onView(withId(R.id.detail_activity_tv)).perform(pressBack())
    }

    @Test
    fun loadBookmark() {
        onView(withId(R.id.tv_main_title)).check(matches(isDisplayed()))
        openActionBarOverflowOrOptionsMenu(
            ApplicationProvider.getApplicationContext()
        )
        onView(withText("Bookmark")).perform(click())

        onView(withText("TREND")).perform(click())
        onView(withText("MOVIE")).perform(click())
        onView(withText("TV SHOW")).perform(click())
    }
}
