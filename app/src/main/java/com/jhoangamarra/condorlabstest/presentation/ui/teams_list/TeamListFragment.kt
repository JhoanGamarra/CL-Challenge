package com.jhoangamarra.condorlabstest.presentation.ui.teams_list

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.jhoangamarra.condorlabstest.R
import com.jhoangamarra.condorlabstest.core.AppConstants
import com.jhoangamarra.condorlabstest.core.extension.invisible
import com.jhoangamarra.condorlabstest.databinding.FragmentTeamListBinding
import com.jhoangamarra.condorlabstest.presentation.models.LeagueModelView
import com.jhoangamarra.condorlabstest.presentation.models.TeamModelView
import com.jhoangamarra.condorlabstest.presentation.ui.teams_list.recyclerview.TeamListAdapter
import dagger.hilt.android.AndroidEntryPoint

private val TAG = TeamListFragment::class.java.simpleName

@AndroidEntryPoint
class TeamListFragment : Fragment(R.layout.fragment_team_list) {

    private lateinit var binding: FragmentTeamListBinding
    private lateinit var adapter: TeamListAdapter


    private val viewModel :TeamListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTeamListBinding.bind(view)
        setupViewModel()
        setupUI()
    }

    //view model
    private fun setupViewModel() {
        viewModel.teams.observe(viewLifecycleOwner, fetchTeams)
        viewModel.leagues.observe(viewLifecycleOwner, fetchLeagues)
        viewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        viewModel.isEmptyList.observe(viewLifecycleOwner, emptyListObserver)
        viewModel.onMessageError.observe(viewLifecycleOwner, isOnMessageError)
    }


    //ui
    private fun setupUI() {
        adapter = TeamListAdapter()
        binding.recyclerView.adapter = adapter
        adapter.onItemClickListener = { product ->
            val action =
                TeamListFragmentDirections.actionTeamListFragmentToTeamDetailFragment(
                    if (product.strTeam.isNotEmpty()) product.strTeam else product.strAlternate,
                    if (product.strDescriptionEN.isNotEmpty()) product.strDescriptionEN else product.strDescriptionES,
                    product.intFormedYear,
                    product.strTeamBadge,
                    product.strTeamJersey,
                    product.strWebsite,
                    product.strFacebook,
                    product.strTwitter,
                    product.strInstagram,
                    product.strYoutube
                )
            findNavController().navigate(action)
        }
        retryFetchTeams()
    }

    private val fetchLeagues = Observer<List<LeagueModelView>> { result ->
        Log.d(TAG, "Teams : $result")
        binding.spinnerLeaguesList.item = result

        binding.spinnerLeaguesList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    viewModel.getTeams(result[position].id)
                }
                override fun onNothingSelected(adapterView: AdapterView<*>) {
                }
            }

    }

    private val fetchTeams = Observer<List<TeamModelView>> { result ->
        Log.d(TAG, "Teams : $result")
        adapter.submitList(result)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.d(TAG, "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        binding.progressBar.visibility = visibility
    }

    private val isOnMessageError = Observer<Any> { e ->
        Log.d(TAG, "Error : $e")
        Toast.makeText(requireContext(), "Error : $e", Toast.LENGTH_SHORT).show()
    }


    private val emptyListObserver = Observer<Boolean> {
        Log.d(TAG, "emptyListObserver $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        binding.ivEmptyView.visibility = visibility
        binding.btnRetry.visibility = visibility
    }

    private fun retryFetchTeams() {
        binding.btnRetry.setOnClickListener {
            viewModel.getTeams(AppConstants.LEAGUE_ID)
            binding.ivEmptyView.invisible()
            binding.btnRetry.invisible()
        }
    }

}