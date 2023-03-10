package com.p413.tddlearning

import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.*
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PlayListFeatureTest : BaseUiTest() {

    @Test
    fun displayTitleAsPlayList() {
        onView(
            CoreMatchers.allOf(
                instanceOf(TextView::class.java),
                withParent(withResourceName("action_bar"))
            )
        ).check(matches(withText("Playlists")))
    }

    @Test
    fun displayListOfPlayLists() {
        onView(
            CoreMatchers.allOf(
                withId(R.id.item_name),
                isDescendantOfA(nthChildOf(withId(R.id.rv_play_list), 0))
            )
        )
            .check(matches(withText(endsWith("Music 1"))))
            .check(matches(isDisplayed()))


        onView(
            CoreMatchers.allOf(
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
        onView(withId(R.id.progress_bar))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun navigateToDetailsScreen() {
        onView(
            CoreMatchers.allOf(
                withId(R.id.playlist_img),
                isDescendantOfA(
                    nthChildOf(
                        withId(R.id.rv_play_list), 0
                    )
                )
            )
        ).perform(ViewActions.click())

        assertDisplayed(R.id.playlist_details_root)
    }

}