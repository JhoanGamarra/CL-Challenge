package com.jhoangamarra.condorlabstest.screens

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.Visibility.INVISIBLE
import androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE
import com.jhoangamarra.condorlabstest.R
import com.jhoangamarra.condorlabstest.presentation.ui.teams_list.TeamListFragment
import com.jhoangamarra.condorlabstest.launchFragmentInHiltContainer
import com.jhoangamarra.condorlabstest.presentation.ui.teams_list.recyclerview.TeamListAdapter
import org.mockito.Mockito.mock
import androidx.recyclerview.widget.RecyclerView

import androidx.test.espresso.matcher.BoundedMatcher

import androidx.annotation.NonNull
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf


class TeamListScreen : Screen() {

    private val navController = mock(NavController::class.java)
    private val leaguesSelector = onView(withId(R.id.spinner_leagues_list))
    private val progressBar = onView(withId(R.id.progressBar))
    private val imageViewEmptyList = onView(withId(R.id.iv_empty_view))
    private val retryButton = onView(withId(R.id.btn_retry))
    private val teamListRecyclerView = onView(withId(R.id.recyclerView))
    private val teamItem = onView(withId(R.id.card_view_team_item))


    override fun verify(): Screen {
        launchFragmentInHiltContainer()
        teamListRecyclerView.check(matches(withEffectiveVisibility(VISIBLE)))
        return this
    }

    private fun launchFragmentInHiltContainer(): TeamListScreen {
        launchFragmentInHiltContainer<TeamListFragment>() {
            Navigation.setViewNavController(requireView(), navController)
        }
        return this
    }


    //Checkers
    fun checkIfProgressBarIsHidden(): TeamListScreen {
        progressBar.check(matches(withEffectiveVisibility(INVISIBLE)))
        return this
    }

    fun checkIfProgressBarShowing(): TeamListScreen {
        progressBar.check(matches(isDisplayed()))
        return this
    }

    fun checkIfEmptyViewIsShowing() : TeamListScreen {
        imageViewEmptyList.check(matches(isDisplayed()))
        return this
    }

    fun checkIfLeaguesSelectorSpinnerIsShowing() : TeamListScreen {
        leaguesSelector.check(matches(isDisplayed()))
        return this
    }

    //Actions
    fun clickRetryButton(): TeamListScreen {
        retryButton.perform(click())
        return this
    }

    fun clickLeaguesSelectorSpinner() : TeamListScreen {
        leaguesSelector.perform(click())
        return this
    }

   fun clickFirstTeamItemCardView() : TeamListScreen {
       onView(allOf(isDisplayed(),
           first(withParent(withId(R.id.recyclerView)))))
           .perform(click())
        return this
    }

    fun clickItemWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<View>(id) as View
                v.performClick()
            }
        }
    }

    fun <T> first(matcher: Matcher<T>): Matcher<T>? {
        return object : BaseMatcher<T>() {
            var isFirst = true
            override fun matches(item: Any): Boolean {
                if (isFirst && matcher.matches(item)) {
                    isFirst = false
                    return true
                }
                return false
            }

            override fun describeTo(description: Description) {
                description.appendText("should return first matching item")
            }
        }
    }


}