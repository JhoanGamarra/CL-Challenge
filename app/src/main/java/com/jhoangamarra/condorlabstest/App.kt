package com.jhoangamarra.condorlabstest

import android.app.Application
import com.jhoangamarra.condorlabstest.di.AppComponent
import com.jhoangamarra.condorlabstest.di.DaggerAppComponent
import dagger.internal.MapFactory.builder

class App : Application() {

    //we are creating an applicationComponent which we'll use to access our dependencies
    val applicationComponent : AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}