package com.jhoangamarra.condorlabstest.data.mappers

import com.jhoangamarra.condorlabstest.data.local.model.LeagueEntity
import com.jhoangamarra.condorlabstest.data.local.model.TeamEntity
import com.jhoangamarra.condorlabstest.data.network.model.league.LeagueNetworkModel
import com.jhoangamarra.condorlabstest.data.network.model.team.TeamNetworkModel
import com.jhoangamarra.condorlabstest.domain.model.LeagueDomainModel
import com.jhoangamarra.condorlabstest.domain.model.TeamDomainModel


fun TeamEntity.toDomainModel(): TeamDomainModel {

    return TeamDomainModel(
        idTeam,
        idLeague,
        strAlternate ?: "Sin nombre alternativo",
        intFormedYear,
        strTeam,
        strStadium ?: "No Stadium",
        strWebsite ?: "",
        strFacebook ?: "",
        strTwitter ?: "",
        strInstagram ?: "",
        strYoutube ?: "",
        strDescriptionEN ?: " No Description",
        strDescriptionES ?: "No Description",
        strTeamBadge,
        strTeamJersey
    )
}

fun TeamDomainModel.toEntityModel(): TeamEntity {

    return TeamEntity(
        idTeam,
        idLeague,
        strAlternate,
        intFormedYear,
        strTeam,
        strStadium,
        strWebsite,
        strFacebook,
        strTwitter,
        strInstagram,
        strYoutube,
        strDescriptionEN,
        strDescriptionES,
        strTeamBadge,
        strTeamJersey
    )
}


fun TeamNetworkModel.toDomainModel(): TeamDomainModel {

    return TeamDomainModel(
        idTeam ?: "",
        idLeague ?:"",
        strAlternate ?: "",
        intFormedYear ?: "",
        strTeam ?: "",
        strStadium ?: "",
        strWebsite ?: "",
        strFacebook ?: "",
        strTwitter ?: "",
        strInstagram ?: "",
        strYoutube ?: "",
        strDescriptionEN ?: "",
        strDescriptionES ?: "",
        strTeamBadge ?: "",
        strTeamJersey ?: ""
    )

}



fun LeagueNetworkModel.toDomainModel(): LeagueDomainModel {
    return LeagueDomainModel(idLeague, strLeague, strLeagueAlternate, strSport)
}

fun LeagueEntity.toDomainModel(): LeagueDomainModel {
    return LeagueDomainModel(id, name, alternateName ?: "", sport ?: "")
}

fun LeagueDomainModel.toEntityModel(): LeagueEntity {
    return LeagueEntity(id, name, alternateName, sport)
}