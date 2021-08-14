package com.jhoangamarra.condorlabstest.teams.presentation.ui.teams_list

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.jhoangamarra.condorlabstest.R
import com.jhoangamarra.condorlabstest.databinding.FragmentTeamListBinding
import com.jhoangamarra.condorlabstest.teams.data.local.AppDatabase
import com.jhoangamarra.condorlabstest.teams.data.local.source.LocalTeamDataSource
import com.jhoangamarra.condorlabstest.teams.data.network.api.RetrofitClient
import com.jhoangamarra.condorlabstest.teams.data.network.source.RemoteTeamDataSource
import com.jhoangamarra.condorlabstest.teams.data.repository.TeamRepositoryImpl
import com.jhoangamarra.condorlabstest.teams.domain.use_cases.GetTeamsByLeagueImpl
import com.jhoangamarra.condorlabstest.teams.presentation.models.TeamModelView
import com.jhoangamarra.condorlabstest.teams.presentation.ui.teams_list.recyclerview.TeamListAdapter

private val TAG = TeamListFragment::class.java.simpleName

class TeamListFragment : Fragment(R.layout.fragment_team_list) {

    private lateinit var binding: FragmentTeamListBinding
    private lateinit var adapter: TeamListAdapter


    private val viewModel by viewModels<TeamListViewModel> {
        TeamListViewModelFactory(
            GetTeamsByLeagueImpl(
                TeamRepositoryImpl(
                    LocalTeamDataSource(
                        AppDatabase.getDatabase(requireContext()).teamDao()
                    ), RemoteTeamDataSource(RetrofitClient.apiService)
                )
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTeamListBinding.bind(view)
        setupViewModel()
        setupUI()
    }


    //view model
    private fun setupViewModel() {
        viewModel.teams.observe(viewLifecycleOwner, fetchTeams)
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
        Glide.with(requireContext()).load(R.drawable.bg_empty_result).centerCrop()
            .into(binding.ivEmptyView)
        val visibility = if (it) View.VISIBLE else View.GONE
        binding.ivEmptyView.visibility = visibility
    }


}