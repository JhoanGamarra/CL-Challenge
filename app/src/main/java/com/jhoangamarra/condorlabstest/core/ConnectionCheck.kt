package com.jhoangamarra.condorlabstest.core

interface ConnectionCheck {

    suspend fun isConnectionAvailable() : Boolean

}