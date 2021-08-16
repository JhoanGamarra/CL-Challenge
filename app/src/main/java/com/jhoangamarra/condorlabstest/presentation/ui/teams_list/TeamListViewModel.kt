package com.jhoangamarra.condorlabstest.presentation.ui.teams_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhoangamarra.condorlabstest.core.AppConstants
import com.jhoangamarra.condorlabstest.core.ResultStatus
import com.jhoangamarra.condorlabstest.domain.model.LeagueDomainModel
import com.jhoangamarra.condorlabstest.domain.model.TeamDomainModel
import com.jhoangamarra.condorlabstest.domain.use_cases.leagues_list.GetLeaguesBySport
import com.jhoangamarra.condorlabstest.domain.use_cases.teams_list.GetTeamsByLeague
import com.jhoangamarra.condorlabstest.presentation.mappers.toModelView
import com.jhoangamarra.condorlabstest.presentation.models.LeagueModelView
import com.jhoangamarra.condorlabstest.presentation.models.TeamModelView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeamListViewModel(
    private val getTeamsByLeague: GetTeamsByLeague,
    private val getLeaguesBySport: GetLeaguesBySport
) : ViewModel() {

    private val _teams = MutableLiveData<List<TeamModelView>>().apply { value = emptyList() }
    val teams: LiveData<List<TeamModelView>> = _teams

    private val _leagues = MutableLiveData<List<LeagueModelView>>().apply { value = emptyList() }
    val leagues: LiveData<List<LeagueModelView>> = _leagues

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    init {
        getTeams(AppConstants.LEAGUE_ID)
        getLeaguesBySport(AppConstants.SPORT)
    }

    //TODO choose league by leagueId in a selector
    fun getTeams(leagueId: String) {

        _teams.value = emptyList()
        _isViewLoading.value = true
        _isEmptyList.value = false
        viewModelScope.launch {
            val result: ResultStatus<List<TeamDomainModel>> = withContext(Dispatchers.IO) {
                getTeamsByLeague.invoke(leagueId)
            }
            _isViewLoading.value = false
            when (result) {
                is ResultStatus.Success -> {
                    if (result.data.isNullOrEmpty()) {
                        _teams.value = emptyList()
                        _isEmptyList.value = true
                    } else {
                        _isEmptyList.value = false
                        _teams.value = result.data.map { it.toModelView() }
                    }
                }
                is ResultStatus.Failure -> {
                    _onMessageError.value = result.exception
                }
            }
        }

    }


    fun getLeaguesBySport(sport: String) {

        viewModelScope.launch {
            val result: ResultStatus<List<LeagueDomainModel>> = withContext(Dispatchers.IO) {
                getLeaguesBySport.invoke(sport)
            }

            when (result) {
                is ResultStatus.Success -> {
                    _leagues.value = result.data.map { it.toModelView() }
                }
                is ResultStatus.Failure -> {
                    _onMessageError.value = result.exception
                }
            }

        }

    }


}