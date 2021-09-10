package com.example.emptyviewbinding

import androidx.recyclerview.widget.DiffUtil
import com.example.emptyviewbinding.data.Person

class ItemDiffUtil: DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }
}