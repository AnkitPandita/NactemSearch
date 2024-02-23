package com.example.nactemsearch.features.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nactemsearch.R
import com.example.nactemsearch.domain.model.Longform

class SearchAdapter : ListAdapter<Longform, SearchAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val lf: TextView

        init {
            lf = itemView.findViewById(R.id.lf)
        }

        fun bind(longform: Longform) {
            lf.text = longform.lf
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Longform>() {
        override fun areItemsTheSame(oldItem: Longform, newItem: Longform) =
            oldItem.lf == newItem.lf

        override fun areContentsTheSame(oldItem: Longform, newItem: Longform) =
            oldItem == newItem

    }
}