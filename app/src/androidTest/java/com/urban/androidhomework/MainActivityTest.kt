package com.urban.androidhomework

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.urban.androidhomework.ui.MainActivity
import com.urban.androidhomework.utils.EspressoIdlingResource
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun flush() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun check_that_user_can_navigate_to_all_screens() {
        // Check if Filter Floating action button is displayed
        // Check that clicking the button shows date filter dialog
        val floatingActionButton = onView(
            allOf(withId(R.id.fab_filter),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0),
                    1),
                isDisplayed()))
        floatingActionButton.perform(click())

        // Check that clicking apply on date filter dialog dismisses the dialog
        val materialButton = onView(
            allOf(withId(android.R.id.button1), withText("Apply"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.buttonPanel),
                        0),
                    3)))
        materialButton.perform(scrollTo(), click())

        // Check that clicking on a characters recyclerview item launches the character details screen and displays the character image
        val recyclerView = onView(
            allOf(withId(R.id.rv_characters),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    0)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(3, click()))

        val imageView = onView(
            allOf(withId(R.id.iv_character_image), withContentDescription("Character Image"),
                childAtPosition(
                    allOf(withId(R.id.image_background),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                            0)),
                    0),
                isDisplayed()))
        imageView.check(matches(isDisplayed()))

        // Check that clicking on a show residents launches the location residents screen and displays a list of characters
        val materialButton2 = onView(
            allOf(withId(R.id.btn_show_location_residents), withText("Show Residents"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("com.google.android.material.card.MaterialCardView")),
                        0),
                    1),
                isDisplayed()))
        materialButton2.perform(click())

        val recyclerView2 = onView(
            allOf(withId(R.id.rv_residents),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0),
                    0),
                isDisplayed()))
        recyclerView2.check(matches(isDisplayed()))

        val linearLayout = onView(
            allOf(childAtPosition(
                childAtPosition(
                    withId(R.id.rv_residents),
                    0),
                0),
                isDisplayed()))
        linearLayout.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>,
        position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent) &&
                    view == parent.getChildAt(position)
            }
        }
    }
}
