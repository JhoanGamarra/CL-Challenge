package com.jhoangamarra.condorlabstest.core

import com.jhoangamarra.condorlabstest.core.ConnectionCheck
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

class InternetConnectionCheck : ConnectionCheck {


    override suspend fun isConnectionAvailable(): Boolean {
       return try {
            val sock = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8", 53)
            sock.connect(socketAddress, 2000)
            sock.close()
            true
        } catch (e: IOException) {
            false
        }
    }


}