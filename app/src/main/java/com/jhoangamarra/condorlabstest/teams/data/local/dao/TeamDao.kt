package com.jhoangamarra.condorlabstest.teams.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jhoangamarra.condorlabstest.teams.data.local.model.TeamEntity

@Dao
interface TeamDao {

    @Query("SELECT * FROM team_table")
    suspend fun getTeams(): List<TeamEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTeam(team: TeamEntity)

}