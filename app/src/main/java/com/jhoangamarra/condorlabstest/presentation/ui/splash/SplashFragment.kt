package com.jhoangamarra.condorlabstest.presentation.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jhoangamarra.condorlabstest.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment(R.layout.fragment_splash) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            delay(2000)// 2 seconds delay
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToTeamListFragment())
        }

    }



}