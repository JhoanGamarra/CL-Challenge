package com.jhoangamarra.condorlabstest.teams.presentation.mappers

import com.jhoangamarra.condorlabstest.teams.domain.model.TeamDomainModel
import com.jhoangamarra.condorlabstest.teams.presentation.models.TeamModelView


fun TeamDomainModel.toModelView(): TeamModelView {

    return TeamModelView(
        idTeam,
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