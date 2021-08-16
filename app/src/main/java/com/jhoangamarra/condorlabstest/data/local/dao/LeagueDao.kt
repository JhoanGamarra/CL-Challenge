package com.jhoangamarra.condorlabstest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jhoangamarra.condorlabstest.data.local.model.LeagueEntity

@Dao
interface LeagueDao {


    @Query("SELECT * FROM league_table where sport = :sportName ")
    suspend fun getLeagues(sportName : String): List<LeagueEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLeague(league: LeagueEntity)

}