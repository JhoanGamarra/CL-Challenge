package com.jhoangamarra.condorlabstest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jhoangamarra.condorlabstest.data.local.model.TeamEntity

@Dao
interface TeamDao {

    @Query("SELECT * FROM team_table where idLeague = :leagueId")
    suspend fun getTeams(leagueId : String): List<TeamEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTeam(team: TeamEntity)

}