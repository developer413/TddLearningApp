package com.p413.tddlearning

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.p413.tddlearning.groovy.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PlayListFeatureTest {

    val activityRule = ActivityScenarioRule(MainActivity::class.java)
        @Rule get

    @Test
    fun displayTitleAsPlayList() {
        onView(
            allOf(
                instanceOf(TextView::class.java),
                withParent(withResourceName("action_bar"))
            )
        ).check(matches(withText("Playlists")))
    }

    @Test
    fun displayListOfPlayLists() {
        Thread.sleep(5000)
        onView(
            allOf(
                withId(R.id.item_name),
                isDescendantOfA(nthChildOf(withId(R.id.rv_play_list), 0))
            )
        )
            .check(matches(withText(endsWith("Music 1"))))
            .check(matches(isDisplayed()))


        onView(
            allOf(
                withId(R.id.item_category),
                isDescendantOfA(nthChildOf(withId(R.id.rv_play_list), 0))
            )
        )
            .check(matches(withText("Rock")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun displayLoaderWhileFetchingPlaylist() {
        assert(true) { R.id.progress_bar }
    }

    @Test
    fun hideLoader() {
        Thread.sleep(5000)
        onView(withId(R.id.progress_bar))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    private fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("position $childPosition of parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) return false
                val parent = view.parent as ViewGroup

                return (parentMatcher.matches(parent)
                        && parent.childCount > childPosition
                        && parent.getChildAt(childPosition) == view)
            }
        }
    }
}