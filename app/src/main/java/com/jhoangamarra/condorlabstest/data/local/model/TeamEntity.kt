package com.jhoangamarra.condorlabstest.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team_table")
data class TeamEntity(
    @PrimaryKey
    @ColumnInfo(name = "idTeam")
    val idTeam: String = "",
    @ColumnInfo(name = "idLeague")
    val idLeague: String = "",
    @ColumnInfo(name = "strAlternate")
    val strAlternate: String? = "",
    @ColumnInfo(name = "intFormedYear")
    val intFormedYear: String = "",
    @ColumnInfo(name = "strTeam")
    val strTeam: String = "",
    @ColumnInfo(name = "strStadium")
    val strStadium: String? = "",
    @ColumnInfo(name = "strWebsite")
    val strWebsite: String? = "",
    @ColumnInfo(name = "strFacebook")
    val strFacebook: String? = "",
    @ColumnInfo(name = "strTwitter")
    val strTwitter: String? = "",
    @ColumnInfo(name = "strInstagram")
    val strInstagram: String? = "",
    @ColumnInfo(name = "strYoutube")
    val strYoutube: String? = "",
    @ColumnInfo(name = "strDescriptionEN")
    val strDescriptionEN: String? = "",
    @ColumnInfo(name = "strDescriptionES")
    val strDescriptionES: String? = "",
    @ColumnInfo(name = "strTeamBadge")
    var strTeamBadge: String = "",
    @ColumnInfo(name = "strTeamJersey")
    var strTeamJersey: String = ""
)