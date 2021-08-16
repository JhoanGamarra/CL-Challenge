package com.jhoangamarra.condorlabstest.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jhoangamarra.condorlabstest.data.local.dao.LeagueDao
import com.jhoangamarra.condorlabstest.data.local.dao.TeamDao
import com.jhoangamarra.condorlabstest.data.local.model.LeagueEntity
import com.jhoangamarra.condorlabstest.data.local.model.TeamEntity


@Database(entities = [TeamEntity::class, LeagueEntity::class], version = 1 , exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun teamDao(): TeamDao
    abstract fun leagueDao() : LeagueDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "condorlabs_db"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}