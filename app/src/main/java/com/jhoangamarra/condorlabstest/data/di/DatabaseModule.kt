package com.jhoangamarra.condorlabstest.data.di

import android.content.Context
import androidx.room.Room
import com.jhoangamarra.condorlabstest.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "condorlabs_db"
        ).build()
    }


}