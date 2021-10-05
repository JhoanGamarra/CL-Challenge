package com.jhoangamarra.condorlabstest.data.di

import com.jhoangamarra.condorlabstest.core.ConnectionCheck
import com.jhoangamarra.condorlabstest.core.InternetConnectionCheck
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
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providesListLeagueRepository(
        localLeagueDataSource: LocalLeagueDataSource,
        remoteLeagueDataSource: RemoteLeagueDataSource,
        connectionCheck: ConnectionCheck
    ): ListLeagueRepository {
        return ListLeagueRepositoryImpl(localLeagueDataSource, remoteLeagueDataSource, connectionCheck)
    }


    @Provides
    @Singleton
    fun providesListTeamsRepository(
        localTeamDataSource: LocalTeamDataSource,
        remoteTeamDataSource: RemoteTeamDataSource, connectionCheck: ConnectionCheck
    ): ListTeamsRepository {
        return ListTeamsRepositoryImpl(localTeamDataSource, remoteTeamDataSource, connectionCheck)
    }

    @Provides
    @Singleton
    fun providesNetworkConnectionCheck() : ConnectionCheck {
        return InternetConnectionCheck()
    }



}