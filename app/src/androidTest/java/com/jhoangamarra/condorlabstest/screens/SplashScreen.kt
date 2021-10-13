package com.jhoangamarra.condorlabstest.screens

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility.GONE
import androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.jhoangamarra.condorlabstest.R
import com.jhoangamarra.condorlabstest.launchFragmentInHiltContainer
import com.jhoangamarra.condorlabstest.presentation.ui.splash.SplashFragment
import com.jhoangamarra.condorlabstest.presentation.ui.splash.SplashFragmentDirections
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.mockito.Mockito
import org.mockito.Mockito.mock

class SplashScreen : Screen() {

    private val startupAnimation = onView(withId(R.id.animationView))
    private val navController = mock(NavController::class.java)

    override fun verify(): Screen {
        launchFragmentInHiltContainer()
        startupAnimation.check(matches(withEffectiveVisibility(VISIBLE)))
        return this
    }

    private fun launchFragmentInHiltContainer(): SplashScreen {
        launchFragmentInHiltContainer<SplashFragment>() {
            Navigation.setViewNavController(requireView(), navController)
        }
        return this
    }

    fun verifyAnimationIsShowingTwoSeconds(): SplashScreen {
        runBlocking {
            delay(2000)
        }
        startupAnimation.check(matches(withEffectiveVisibility(GONE)))
        return this
    }

    fun navigateToTeamListFragment() : SplashScreen{
        Mockito.verify(navController).navigate(SplashFragmentDirections.actionSplashFragmentToTeamListFragment())
        return this
    }



}