package com.urban.androidhomework.ui


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.urban.androidhomework.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val floatingActionButton = onView(
            allOf(withId(R.id.fab_filter),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0),
                    1),
                isDisplayed()))
        floatingActionButton.perform(click())

        val frameLayout = onView(
            allOf(IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java), isDisplayed()))
        frameLayout.check(matches(isDisplayed()))

        val materialButton = onView(
            allOf(withId(android.R.id.button1), withText("Apply"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.buttonPanel),
                        0),
                    3)))
        materialButton.perform(scrollTo(), click())

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

        val viewGroup = onView(
            allOf(childAtPosition(
                childAtPosition(
                    IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                    1),
                0),
                isDisplayed()))
        viewGroup.check(matches(isDisplayed()))

        val viewGroup2 = onView(
            allOf(childAtPosition(
                childAtPosition(
                    IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                    2),
                0),
                isDisplayed()))
        viewGroup2.check(matches(isDisplayed()))

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

        val appCompatImageButton = onView(
            allOf(withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0)),
                    1),
                isDisplayed()))
        appCompatImageButton.perform(click())

        val imageView2 = onView(
            allOf(withId(R.id.iv_character_image), withContentDescription("Character Image"),
                childAtPosition(
                    allOf(withId(R.id.image_background),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                            0)),
                    0),
                isDisplayed()))
        imageView2.check(matches(isDisplayed()))

        val appCompatImageButton2 = onView(
            allOf(withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0)),
                    1),
                isDisplayed()))
        appCompatImageButton2.perform(click())

        val imageButton = onView(
            allOf(withId(R.id.fab_filter),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0),
                    2),
                isDisplayed()))
        imageButton.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                    && view == parent.getChildAt(position)
            }
        }
    }
}
