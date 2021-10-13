package com.jhoangamarra.condorlabstest.presentation.ui.teams_list

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.jhoangamarra.condorlabstest.screens.Screen.Companion.on
import com.jhoangamarra.condorlabstest.screens.TeamListScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@HiltAndroidTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class TeamListFragmentTest {


    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    @SmallTest
    fun checkIfTeamListFragmentIsShowingCorrectly(){
        on<TeamListScreen>()
            .checkIfLeaguesSelectorSpinnerIsShowing()
    }

    @Test
    @SmallTest
    fun givenClickOnRecyclerViewItemThenShouldGoToTeamDetail(){
        on<TeamListScreen>()
            .clickFirstTeamItemCardView()
    }


}