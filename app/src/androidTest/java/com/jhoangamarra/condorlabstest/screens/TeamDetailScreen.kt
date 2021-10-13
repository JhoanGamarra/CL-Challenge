package com.jhoangamarra.condorlabstest.screens

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.jhoangamarra.condorlabstest.R
import com.jhoangamarra.condorlabstest.presentation.ui.team_detail.TeamDetailFragment
import com.jhoangamarra.condorlabstest.launchFragmentInHiltContainer
import org.mockito.Mockito

class TeamDetailScreen : Screen() {

    private val navController = Mockito.mock(NavController::class.java)
    private val backButton = onView(withId(R.id.ibBackButton))
    private val iVTeamJersey = onView(withId(R.id.iv_team_jersey))
    private val ivTeamBadgeDetail = onView(withId(R.id.iv_team_badge_detail))
    private val tvTeamNameDetail = onView(withId(R.id.tv_team_name_detail))
    private val icFacebookImageButton = onView(withId(R.id.ic_facebook))
    private val icTwitterImageButton = onView(withId(R.id.ic_twitter))
    private val icInstagramImageButton = onView(withId(R.id.ic_instagram))
    private val icYoutubeImageButton = onView(withId(R.id.ic_youtube))
    private val icWebsiteImageButton = onView(withId(R.id.ic_website))
    private val labelOverviewTextView = onView(withId(R.id.labelOverview))
    private val foundationYearTextView = onView(withId(R.id.tv_foundation_year))
    private val teamDescription = onView(withId(R.id.tv_team_description))

    override fun verify(): Screen {
        launchFragmentInHiltContainer()
        backButton.check(matches(isDisplayed()))
        return this
    }

    private fun launchFragmentInHiltContainer(): TeamDetailScreen {
        launchFragmentInHiltContainer<TeamDetailFragment>() {
            Navigation.setViewNavController(requireView(), navController)
        }
        return this
    }

    //Checkers

    fun checkIfBackButtonIsShowing() : TeamDetailScreen {
        backButton.check(matches(isDisplayed()))
        return this
    }

    fun checkIfLabelOverviewIsShowing() : TeamDetailScreen {
        labelOverviewTextView.check(matches(isDisplayed()))
        return this
    }


    //Actions

    fun clickBackButton() : TeamDetailScreen {
        backButton.perform(click())
        return this
    }

    fun clickFacebookImageButton() : TeamDetailScreen {
        icFacebookImageButton.perform(click())
        return this
    }

    fun clickTwitterImageButton() : TeamDetailScreen{
        icTwitterImageButton.perform(click())
        return this
    }

    fun clickInstagramImageButton() : TeamDetailScreen{
        icInstagramImageButton.perform(click())
        return this
    }

    fun clickYoutubeImageButton() : TeamDetailScreen{
        icYoutubeImageButton.perform(click())
        return this
    }

    fun clickWebsiteImageButton() : TeamDetailScreen{
        icWebsiteImageButton.perform(click())
        return this
    }





}