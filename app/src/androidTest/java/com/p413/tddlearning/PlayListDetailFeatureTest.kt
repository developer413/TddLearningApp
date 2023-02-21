package com.p413.tddlearning

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.hamcrest.CoreMatchers
import org.junit.Test

class PlayListDetailFeatureTest: BaseUiTest() {

    @Test
    fun displayPlayListNameAndDetails(){
        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.withId(R.id.playlist_img),
                ViewMatchers.isDescendantOfA(
                    nthChildOf(
                        ViewMatchers.withId(R.id.rv_play_list), 0
                    )
                )
            )
        ).perform(ViewActions.click())

        assertDisplayed("Hard Rock Cafe")
    }
}