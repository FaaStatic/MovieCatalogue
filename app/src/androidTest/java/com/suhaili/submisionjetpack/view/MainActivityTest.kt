package com.suhaili.submisionjetpack.view


import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.suhaili.submisionjetpack.R
import com.suhaili.submisionjetpack.util.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {


    @Before
    fun Prepare() {
        IdlingRegistry.getInstance()
            .register(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
        ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun Done() {
        IdlingRegistry.getInstance()
            .unregister(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
    }


    @Test
    fun TestListMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                20
            )
        )

    }


    @Test
    fun TestListTV() {
        onView(withText(R.string.TabTV)).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                20
            )
        )
    }


    @Test
    fun TestDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.originalTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.imagepicMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.genreDetailmovie)).check(matches(isDisplayed()))
        onView(withId(R.id.releaseMovie)).check(matches(isDisplayed()))
    }

    @Test
    fun TestDetailTV() {
        onView(withText(R.string.TabTV)).perform(click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.originalTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.imagepicMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.genreDetailmovie)).check(matches(isDisplayed()))
        onView(withId(R.id.releaseMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.lastair)).check(matches(isDisplayed()))
    }


    @Test
    fun TestFavoriteMovie() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.favoriteMovie)).perform(click())

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());

        onView(withId(R.id.FavoriteMenu)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))

        Espresso.pressBack()

        onView(withId(R.id.FavoriteMenu)).perform(click())
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.originalTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.imagepicMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.genreDetailmovie)).check(matches(isDisplayed()))
        onView(withId(R.id.releaseMovie)).check(matches(isDisplayed()))

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());

        onView(withId(R.id.FavoriteMenu)).perform(click())
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.favoriteMovie)).perform(click())

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());

        onView(withId(R.id.FavoriteMenu)).perform(click())

    }

    @Test
    fun TestFavouriteTV() {
        onView(withText(R.string.TabTV)).perform(click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.favoriteTV)).perform(click())

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());

        onView(withId(R.id.FavoriteMenu)).perform(click())
        onView(withText("TV Favourite")).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))

        Espresso.pressBack()

        onView(withId(R.id.FavoriteMenu)).perform(click())
        onView(withText("TV Favourite")).perform(click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.originalTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.imagepicMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.genreDetailmovie)).check(matches(isDisplayed()))
        onView(withId(R.id.releaseMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.lastair)).check(matches(isDisplayed()))

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());

        onView(withId(R.id.FavoriteMenu)).perform(click())
        onView(withText("TV Favourite")).perform(click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.favoriteTV)).perform(click())

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());

        onView(withId(R.id.FavoriteMenu)).perform(click())
    }


}