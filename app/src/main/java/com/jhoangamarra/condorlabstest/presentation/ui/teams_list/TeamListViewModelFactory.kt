package com.jhoangamarra.condorlabstest.presentation.ui.teams_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jhoangamarra.condorlabstest.domain.use_cases.leagues_list.GetLeaguesBySport
import com.jhoangamarra.condorlabstest.domain.use_cases.teams_list.GetTeamsByLeague
import javax.inject.Inject

class TeamListViewModelFactory @Inject constructor(
    private val getTeamsByLeague: GetTeamsByLeague,
    private val getLeaguesBySport: GetLeaguesBySport
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TeamListViewModel(getTeamsByLeague,getLeaguesBySport) as T
    }

}