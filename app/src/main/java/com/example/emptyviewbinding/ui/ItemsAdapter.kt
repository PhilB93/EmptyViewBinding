package com.example.emptyviewbinding.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.emptyviewbinding.R
import com.example.emptyviewbinding.data.Person
import com.example.emptyviewbinding.databinding.RowItemBinding

class ItemsAdapter: ListAdapter<Person, ItemsAdapter.MainViewHolder>(ItemDiffUtil()) {

    class MainViewHolder(val binding: RowItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onViewAttachedToWindow(holder: MainViewHolder) {
        holder.itemView.setOnClickListener {
            val action  =
                MainFragmentDirections.actionMainFragmentToUpdateFragment(currentList[holder.bindingAdapterPosition])
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun onViewDetachedFromWindow(holder: MainViewHolder) {
        super.onViewDetachedFromWindow(holder)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.tvName.text = getItem(position).name.toString()
        holder.binding.tvAge.text = getItem(position).age.toString()
        if (getItem(position).skin ==0)
        holder.binding.icon.setImageResource(R.drawable.blackl)
        else holder.binding.icon.setImageResource(R.drawable.white)

    }
}