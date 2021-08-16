package com.jhoangamarra.condorlabstest.presentation.models


data class LeagueModelView(val id: String, val name: String){

    override fun toString(): String {
        return name
    }

}
