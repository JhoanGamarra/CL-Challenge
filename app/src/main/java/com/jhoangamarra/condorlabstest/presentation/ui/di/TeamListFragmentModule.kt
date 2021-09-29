package com.jhoangamarra.condorlabstest.presentation.ui.di

import com.jhoangamarra.condorlabstest.domain.repository.ListLeagueRepository
import com.jhoangamarra.condorlabstest.domain.repository.ListTeamsRepository
import com.jhoangamarra.condorlabstest.domain.use_cases.leagues_list.GetLeaguesBySport
import com.jhoangamarra.condorlabstest.domain.use_cases.leagues_list.GetLeaguesBySportImpl
import com.jhoangamarra.condorlabstest.domain.use_cases.teams_list.GetTeamsByLeague
import com.jhoangamarra.condorlabstest.domain.use_cases.teams_list.GetTeamsByLeagueImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
class TeamListFragmentModule {

    //El viewmodelComponent ya provee el viewmodel no es necesario hacerlo como un provides

    @Provides
    @ViewModelScoped
    fun providesGetTeamsByLeague(repository: ListTeamsRepository) : GetTeamsByLeague{
        return GetTeamsByLeagueImpl(repository)
    }

    @Provides
    @ViewModelScoped
    fun providesGetLeaguesBySport(repository: ListLeagueRepository) : GetLeaguesBySport{
        return GetLeaguesBySportImpl(repository)
    }

}