package com.jhoangamarra.condorlabstest.presentation.mappers

import com.jhoangamarra.condorlabstest.domain.model.LeagueDomainModel
import com.jhoangamarra.condorlabstest.domain.model.TeamDomainModel
import com.jhoangamarra.condorlabstest.presentation.models.LeagueModelView
import com.jhoangamarra.condorlabstest.presentation.models.TeamModelView


fun TeamDomainModel.toModelView(): TeamModelView {

    return TeamModelView(
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


fun LeagueDomainModel.toModelView(): LeagueModelView {
    return LeagueModelView(id, name)
}