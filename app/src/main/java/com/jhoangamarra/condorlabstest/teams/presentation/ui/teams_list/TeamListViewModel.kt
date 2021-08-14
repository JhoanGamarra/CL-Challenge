package com.jhoangamarra.condorlabstest.teams.presentation.ui.teams_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhoangamarra.condorlabstest.core.AppConstants
import com.jhoangamarra.condorlabstest.teams.domain.model.TeamDomainModel
import com.jhoangamarra.condorlabstest.teams.domain.use_cases.GetTeamsByLeague
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.jhoangamarra.condorlabstest.core.Result
import com.jhoangamarra.condorlabstest.teams.presentation.mappers.toModelView
import com.jhoangamarra.condorlabstest.teams.presentation.models.TeamModelView

class TeamListViewModel(private val getTeamsByLeague: GetTeamsByLeague) : ViewModel() {

    private val _teams = MutableLiveData<List<TeamModelView>>().apply { value = emptyList() }
    val teams: LiveData<List<TeamModelView>> = _teams

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    init {
        getTeams(AppConstants.LEAGUE_ID)
    }

    //TODO choose league by leagueId in a selector
    private fun getTeams(leagueId : String ) {

        _isViewLoading.value = true
        viewModelScope.launch {
            val result: Result<List<TeamDomainModel>> = withContext(Dispatchers.IO){
                getTeamsByLeague.invoke(leagueId)
            }
            _isViewLoading.value = false
            when(result){
                is Result.Success ->{
                    if(result.data.isNullOrEmpty()){
                        _isEmptyList.value = true
                    }else{
                        _isEmptyList.value = false
                        _teams.value = result.data.map { it.toModelView()}
                    }
                }
                is Result.Failure ->{
                    _onMessageError.value = result.exception
                }
            }

        }

    }




}