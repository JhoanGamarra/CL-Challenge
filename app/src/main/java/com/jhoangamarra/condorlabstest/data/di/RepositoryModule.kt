package com.jhoangamarra.condorlabstest.data.di

import com.jhoangamarra.condorlabstest.data.local.source.LocalLeagueDataSource
import com.jhoangamarra.condorlabstest.data.local.source.LocalTeamDataSource
import com.jhoangamarra.condorlabstest.data.network.source.RemoteLeagueDataSource
import com.jhoangamarra.condorlabstest.data.network.source.RemoteTeamDataSource
import com.jhoangamarra.condorlabstest.data.repository.ListLeagueRepositoryImpl
import com.jhoangamarra.condorlabstest.data.repository.ListTeamsRepositoryImpl
import com.jhoangamarra.condorlabstest.domain.repository.ListLeagueRepository
import com.jhoangamarra.condorlabstest.domain.repository.ListTeamsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesListLeagueRepository(
        localLeagueDataSource: LocalLeagueDataSource,
        remoteLeagueDataSource: RemoteLeagueDataSource
    ): ListLeagueRepository {
        return ListLeagueRepositoryImpl(localLeagueDataSource, remoteLeagueDataSource)
    }


    @Provides
    @Singleton
    fun providesListTeamsRepository(
        localTeamDataSource: LocalTeamDataSource,
        remoteTeamDataSource: RemoteTeamDataSource
    ): ListTeamsRepository {
        return ListTeamsRepositoryImpl(localTeamDataSource, remoteTeamDataSource)
    }


}