package com.jhoangamarra.condorlabstest.data.di

import com.jhoangamarra.condorlabstest.data.local.AppDatabase
import com.jhoangamarra.condorlabstest.data.local.dao.LeagueDao
import com.jhoangamarra.condorlabstest.data.local.dao.TeamDao
import com.jhoangamarra.condorlabstest.data.local.source.LocalLeagueDataSource
import com.jhoangamarra.condorlabstest.data.local.source.LocalTeamDataSource
import com.jhoangamarra.condorlabstest.data.network.api.ApiService
import com.jhoangamarra.condorlabstest.data.network.source.RemoteLeagueDataSource
import com.jhoangamarra.condorlabstest.data.network.source.RemoteTeamDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataSourceModule {

    @Singleton
    @Provides
    fun providesLocalTeamDataSource(database: AppDatabase) : LocalTeamDataSource{
        return LocalTeamDataSource(database.teamDao())
    }

    @Singleton
    @Provides
    fun providesLocalLeagueDataSource(database: AppDatabase) : LocalLeagueDataSource{
        return LocalLeagueDataSource(database.leagueDao())
    }


    @Singleton
    @Provides
    fun providesRemoteLeagueDataSource(api: ApiService) : RemoteLeagueDataSource{
        return RemoteLeagueDataSource(api)
    }


    @Singleton
    @Provides
    fun providesRemoteTeamDataSource(api: ApiService) : RemoteTeamDataSource{
        return RemoteTeamDataSource(api)
    }


}