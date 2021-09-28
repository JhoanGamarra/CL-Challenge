package com.jhoangamarra.condorlabstest.di

import android.content.Context
import com.jhoangamarra.condorlabstest.data.di.ApiModule
import com.jhoangamarra.condorlabstest.data.di.DataSourceModule
import com.jhoangamarra.condorlabstest.data.di.DatabaseModule
import com.jhoangamarra.condorlabstest.data.di.RepositoryModule
import com.jhoangamarra.condorlabstest.domain.di.UseCasesModule
import com.jhoangamarra.condorlabstest.domain.repository.ListLeagueRepository
import com.jhoangamarra.condorlabstest.domain.repository.ListTeamsRepository
import com.jhoangamarra.condorlabstest.presentation.ui.teams_list.TeamListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class , DatabaseModule::class , DataSourceModule::class, RepositoryModule::class, UseCasesModule::class])
interface AppComponent {

    fun inject(teamListFragment: TeamListFragment)

    //Method create() is the one we'll use to create an instance of ApplicationComponent
    // Context is needed to create instances of MyDatabase
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

}