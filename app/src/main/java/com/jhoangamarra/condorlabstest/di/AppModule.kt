package com.jhoangamarra.condorlabstest.di

import android.content.Context
import androidx.room.Room
import com.jhoangamarra.condorlabstest.data.local.AppDatabase
import com.jhoangamarra.condorlabstest.data.local.source.LocalLeagueDataSource
import com.jhoangamarra.condorlabstest.data.local.source.LocalTeamDataSource
import com.jhoangamarra.condorlabstest.data.network.api.ApiService
import com.jhoangamarra.condorlabstest.data.network.source.RemoteLeagueDataSource
import com.jhoangamarra.condorlabstest.data.network.source.RemoteTeamDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "condorlabs_db"
    ).build()

    @Singleton
    @Provides
    fun providesLocalTeamDataSource(database: AppDatabase) = LocalTeamDataSource(database.teamDao())

    @Singleton
    @Provides
    fun providesLocalLeagueDataSource(database: AppDatabase) =
        LocalLeagueDataSource(database.leagueDao())


    @Singleton
    @Provides
    fun providesRemoteLeagueDataSource(api: ApiService) = RemoteLeagueDataSource(api)


    @Singleton
    @Provides
    fun providesRemoteTeamDataSource(api: ApiService) = RemoteTeamDataSource(api)

}