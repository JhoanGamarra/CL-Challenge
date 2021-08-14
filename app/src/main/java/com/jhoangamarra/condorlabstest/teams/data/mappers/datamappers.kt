package com.jhoangamarra.condorlabstest.teams.data.mappers

import com.jhoangamarra.condorlabstest.teams.data.local.model.TeamEntity
import com.jhoangamarra.condorlabstest.teams.data.network.model.NetworkTeam
import com.jhoangamarra.condorlabstest.teams.domain.model.TeamDomainModel


fun TeamEntity.toDomainModel(): TeamDomainModel {

    return TeamDomainModel(
        idTeam,
        strAlternate ?: "Sin nombre alternativo",
        intFormedYear,
        strTeam,
        strStadium?: "No Stadium",
        strWebsite ?: "",
        strFacebook?: "",
        strTwitter?: "",
        strInstagram?: "",
        strYoutube?: "",
        strDescriptionEN ?: " No Description",
        strDescriptionES ?: "No Description",
        strTeamBadge,
        strTeamJersey
    )
}

fun TeamDomainModel.toEntityModel(): TeamEntity {

    return TeamEntity(
        idTeam,
        strAlternate,
        intFormedYear,
        strTeam,
        strStadium,
        strWebsite ?: "",
        strFacebook ?: "",
        strTwitter ?: "",
        strInstagram ?: "",
        strYoutube ?: "",
        strDescriptionEN,
        strDescriptionES,
        strTeamBadge,
        strTeamJersey
    )
}


fun NetworkTeam.toDomainModel(): TeamDomainModel {

    return TeamDomainModel(
        idTeam,
        strAlternate ?: "",
        intFormedYear,
        strTeam,
        strStadium,
        strWebsite?: "",
        strFacebook?: "",
        strTwitter?: "",
        strInstagram?: "",
        strYoutube?: "",
        strDescriptionEN?: "",
        strDescriptionES?: "",
        strTeamBadge,
        strTeamJersey
    )

}