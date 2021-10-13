package com.jhoangamarra.condorlabstest.presentation.ui.splash

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.jhoangamarra.condorlabstest.launchFragmentInHiltContainer
import com.jhoangamarra.condorlabstest.screens.Screen.Companion.on
import com.jhoangamarra.condorlabstest.screens.SplashScreen
import com.jhoangamarra.condorlabstest.screens.TeamListScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.verify

@HiltAndroidTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SplashFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    @SmallTest
    fun verifyIfSplashIsShowingCorrectly() {
        on<SplashScreen>()
            .verifyAnimationIsShowingTwoSeconds()
    }

    @Test
    @SmallTest
    fun givenStartApplicationThenShouldOpenSplashAndNavigateToTeamListFragmentAfterTwoSeconds() {

        on<SplashScreen>()
            .verifyAnimationIsShowingTwoSeconds()
            .navigateToTeamListFragment()
            .on<TeamListScreen>()

    }


}

