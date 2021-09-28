package com.jhoangamarra.condorlabstest.presentation.ui.di

import androidx.lifecycle.ViewModelProvider
import com.jhoangamarra.condorlabstest.domain.use_cases.leagues_list.GetLeaguesBySport
import com.jhoangamarra.condorlabstest.domain.use_cases.teams_list.GetTeamsByLeague
import com.jhoangamarra.condorlabstest.presentation.ui.teams_list.TeamListFragment
import com.jhoangamarra.condorlabstest.presentation.ui.teams_list.TeamListViewModel
import com.jhoangamarra.condorlabstest.presentation.ui.teams_list.TeamListViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class ViewModelModule {


    @Provides
    fun providesViewModelFactory(
        getTeamsByLeague: GetTeamsByLeague,
        getLeaguesBySport: GetLeaguesBySport
    ) : TeamListViewModelFactory {
        return TeamListViewModelFactory(getTeamsByLeague, getLeaguesBySport)
    }

    //We're telling Dagger how to create instances of MainViewModel
    //AppCompactActivity will be retrieved from MainComponent
    @Provides
    fun provideMainViewModel( fragment: TeamListFragment , getTeamsByLeague: GetTeamsByLeague,
                              getLeaguesBySport: GetLeaguesBySport): TeamListViewModel {
        val factory = TeamListViewModelFactory(getTeamsByLeague, getLeaguesBySport)
        return ViewModelProvider(fragment, factory).get(TeamListViewModel::class.java)
    }

}