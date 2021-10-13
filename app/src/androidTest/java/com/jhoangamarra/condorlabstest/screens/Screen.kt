package com.jhoangamarra.condorlabstest.screens

import androidx.test.espresso.Espresso.pressBack

open class Screen {

    companion object {
        inline fun <reified  T : Screen> on() : T {
            return Screen().on()
        }
    }

    inline fun <reified T : Screen> on(): T{
        val page = T::class.constructors.first().call()
        page.verify()
        return page
    }

    open fun verify() : Screen {
        //Each subpage should have its defaults assurance here
        return this
    }

    fun back(): Screen {
        pressBack()
        return this
    }

    fun wait(seconds: Int): Screen {
        Thread.sleep(seconds * 1000L)
        return this
    }
}