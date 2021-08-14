package com.jhoangamarra.condorlabstest.teams.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jhoangamarra.condorlabstest.R
import com.jhoangamarra.condorlabstest.teams.presentation.ui.teams_list.TeamListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    //TODO Crashlytics , Fix emptyView, league selector , add testing
    override fun onBackPressed() {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        navHost?.let { navFragment ->
            navFragment.childFragmentManager.primaryNavigationFragment?.let { fragment ->
                if (fragment is TeamListFragment) {
                    finish()
                } else {
                    super.onBackPressed()
                }
            }
        }
    }

}