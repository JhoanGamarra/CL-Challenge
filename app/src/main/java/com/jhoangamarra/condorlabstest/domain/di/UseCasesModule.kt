package com.jhoangamarra.condorlabstest.domain.di

import com.jhoangamarra.condorlabstest.domain.repository.ListLeagueRepository
import com.jhoangamarra.condorlabstest.domain.repository.ListTeamsRepository
import com.jhoangamarra.condorlabstest.domain.use_cases.leagues_list.GetLeaguesBySport
import com.jhoangamarra.condorlabstest.domain.use_cases.leagues_list.GetLeaguesBySportImpl
import com.jhoangamarra.condorlabstest.domain.use_cases.teams_list.GetTeamsByLeague
import com.jhoangamarra.condorlabstest.domain.use_cases.teams_list.GetTeamsByLeagueImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class UseCasesModule {

    @Singleton
    @Provides
    fun providesGetTeamsByLeague(repository: ListTeamsRepository) : GetTeamsByLeague{
        return GetTeamsByLeagueImpl(repository)
    }

    @Singleton
    @Provides
    fun providesGetLeaguesBySport(repository: ListLeagueRepository) : GetLeaguesBySport{
        return GetLeaguesBySportImpl(repository)
    }


}