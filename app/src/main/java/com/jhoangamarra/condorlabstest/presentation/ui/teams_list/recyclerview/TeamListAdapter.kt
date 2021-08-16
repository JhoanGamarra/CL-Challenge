package com.jhoangamarra.condorlabstest.presentation.ui.teams_list.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jhoangamarra.condorlabstest.R
import com.jhoangamarra.condorlabstest.core.extension.loadFromUrl
import com.jhoangamarra.condorlabstest.databinding.ItemTeamBinding
import com.jhoangamarra.condorlabstest.presentation.models.TeamModelView

private val TAG = TeamListAdapter::class.java.simpleName

class TeamListAdapter  : ListAdapter<TeamModelView, TeamListAdapter.ProductViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<TeamModelView>() {
        override fun areItemsTheSame(
            oldItem: TeamModelView,
            newItem: TeamModelView
        ): Boolean {
            return oldItem.idTeam == newItem.idTeam
        }

        override fun areContentsTheSame(
            oldItem: TeamModelView,
            newItem: TeamModelView
        ): Boolean {
            return oldItem == newItem
        }
    }


    lateinit var onItemClickListener: (TeamModelView) -> Unit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }


    inner class ProductViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(product: TeamModelView) {

            ItemTeamBinding.bind(view).apply {
                tvTeamName.text = if (product.strTeam.isNotEmpty()) product.strTeam else product.strAlternate
                tvStadiumName.text = product.strStadium
                ivTeamBadge.loadFromUrl(product.strTeamBadge)
            }

            view.setOnClickListener {
                if (::onItemClickListener.isInitialized) {
                    onItemClickListener(product)
                } else {
                    Log.e(TAG, "onItemClickListener not initialized")
                }
            }
        }
    }

}