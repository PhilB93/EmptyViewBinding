package com.example.emptyviewbinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.emptyviewbinding.data.NbaPlayer
import com.example.emptyviewbinding.databinding.RowItemBinding

class ItemsAdapter: ListAdapter<NbaPlayer, ItemsAdapter.MainViewHolder>(ItemDiffUtil()) {

    class MainViewHolder(val binding: RowItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onViewAttachedToWindow(holder: MainViewHolder) {
        holder.itemView.setOnClickListener {
        //    MainFragment.click(currentList[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MainViewHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.itemName.text = getItem(position).name
        holder.binding.itemAge.text = getItem(position).age.toString()

    }
}