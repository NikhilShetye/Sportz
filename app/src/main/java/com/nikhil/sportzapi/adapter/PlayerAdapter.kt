package com.nikhil.sportzapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nikhil.sportzapi.R
import com.nikhil.sportzapi.model.VisiblePlayer


class PlayerAdapter(private val dataSet: ArrayList<VisiblePlayer>,private val onClick: (VisiblePlayer) -> Unit) :
    RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {
    class ViewHolder(view: View,val onClick: (VisiblePlayer) -> Unit) : RecyclerView.ViewHolder(view) {
        private var currentPlayer : VisiblePlayer? =null
        private val textView: TextView

        init {
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.tv_player_name)
            view.setOnClickListener {
                currentPlayer?.let {
                    onClick(it)
                }
            }
        }

        /* Bind player name. */
        fun bind(visiblePlayer: VisiblePlayer, position: Int) {
            currentPlayer = visiblePlayer
            var name = currentPlayer!!.name
            val captain = " (C) "
            val keeper = " (WK) "
            if(visiblePlayer.isCaptain) {
                name += captain
                textView.text = name
            }else{
                textView.text = name
            }
            if(visiblePlayer.isKeeper) {
                name += keeper
                textView.text = name
            }else{
                textView.text = name
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.player_item, viewGroup, false)

        return ViewHolder(view,onClick)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val playerName = dataSet[position].name
        viewHolder.bind(dataSet[position],position)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun updatePlayerList(newPlayerList: ArrayList<VisiblePlayer>){
        dataSet.clear();
        dataSet.addAll(newPlayerList)
        notifyDataSetChanged()
    }

}
