package com.jhoangamarra.condorlabstest.teams.presentation.ui.teams_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jhoangamarra.condorlabstest.teams.domain.use_cases.GetTeamsByLeague

class TeamListViewModelFactory(private val getTeamsByLeague: GetTeamsByLeague) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetTeamsByLeague::class.java).newInstance(getTeamsByLeague)
    }
}