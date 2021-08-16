package com.jhoangamarra.condorlabstest.presentation.ui.team_detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jhoangamarra.condorlabstest.R
import com.jhoangamarra.condorlabstest.core.extension.invisible
import com.jhoangamarra.condorlabstest.core.extension.loadFromUrl
import com.jhoangamarra.condorlabstest.databinding.FragmentTeamDetailBinding

class TeamDetailFragment : Fragment(R.layout.fragment_team_detail) {

    private lateinit var binding : FragmentTeamDetailBinding
    private val args by navArgs<TeamDetailFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTeamDetailBinding.bind(view)
        setupView()
        onBackButtonPressed()
    }

    private fun onBackButtonPressed() {
        binding.ibBackButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupView() {
        binding.tvTeamNameDetail.text = args.teamName
        binding.tvTeamDescription.text = args.teamDescription
        args.teamBadgeImage?.let { binding.ivTeamBadgeDetail.loadFromUrl(it)}
        args.teamJerseyImage?.let { binding.ivTeamJersey.loadFromUrl(it) }
        binding.tvFoundationYear.text = args.teamFoundationYear
        if (args.facebookUrl?.isNotEmpty() == true)  binding.icFacebook.setOnClickListener { openExternalLink(args.facebookUrl!!) } else binding.icFacebook.invisible()
        if (args.twitterUrl?.isNotEmpty() == true)  binding.icTwitter.setOnClickListener { openExternalLink(args.twitterUrl!!) } else binding.icTwitter.invisible()
        if (args.youtubeUrl?.isNotEmpty() == true)  binding.icYoutube.setOnClickListener { openExternalLink(args.youtubeUrl!!) } else binding.icYoutube.invisible()
        if (args.instagramUrl?.isNotEmpty() == true)  binding.icInstagram.setOnClickListener { openExternalLink(args.instagramUrl!!) } else binding.icInstagram.invisible()
        if (args.websiteUrl?.isNotEmpty() == true)  binding.icWebsite.setOnClickListener { openExternalLink(args.websiteUrl!!) } else binding.icWebsite.invisible()
    }


    private fun openExternalLink(url : String){
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse("https://$url")
        startActivity(openURL)
    }


}