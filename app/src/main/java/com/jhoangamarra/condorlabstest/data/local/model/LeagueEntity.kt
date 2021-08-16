package com.jhoangamarra.condorlabstest.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "league_table")
data class LeagueEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = "",
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "alternateName")
    val alternateName: String? = "",
    @ColumnInfo(name = "sport")
    val sport: String? = "",
    )
