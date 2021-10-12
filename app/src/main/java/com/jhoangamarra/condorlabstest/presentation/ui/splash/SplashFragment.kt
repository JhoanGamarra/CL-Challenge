package com.jhoangamarra.condorlabstest.presentation.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jhoangamarra.condorlabstest.R
import com.jhoangamarra.condorlabstest.core.extension.invisible
import com.jhoangamarra.condorlabstest.core.extension.visible
import com.jhoangamarra.condorlabstest.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.wait

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var binding : FragmentSplashBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSplashBinding.bind(view)

       lifecycleScope.launch {
            delay(2000)// 2 seconds delay
            binding.animationView.invisible()
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToTeamListFragment())
        }

    }



}