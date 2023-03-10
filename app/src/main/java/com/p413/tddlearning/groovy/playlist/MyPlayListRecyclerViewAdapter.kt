package com.p413.tddlearning.groovy.playlist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.p413.tddlearning.databinding.PlaylistItemBinding


class MyPlayListRecyclerViewAdapter(
    private val values: List<PlayList>,
    private val onItemClick: (String) -> Unit,
) : RecyclerView.Adapter<MyPlayListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            PlaylistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.playListName.text = item.name
        holder.playlistCategory.text = item.category
        holder.playListImage.setImageResource(item.image)
        holder.root.setOnClickListener {
            onItemClick(item.id)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: PlaylistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val playListName: TextView = binding.itemName
        val playlistCategory: TextView = binding.itemCategory
        val playListImage: ImageView = binding.playlistImg
        val root: LinearLayout = binding.rootItem
    }

}