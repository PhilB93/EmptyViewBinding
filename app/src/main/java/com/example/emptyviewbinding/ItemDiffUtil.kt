package com.example.emptyviewbinding

import androidx.recyclerview.widget.DiffUtil
import com.example.emptyviewbinding.data.NbaPlayer

class ItemDiffUtil: DiffUtil.ItemCallback<NbaPlayer>() {
    override fun areItemsTheSame(oldItem: NbaPlayer, newItem: NbaPlayer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NbaPlayer, newItem: NbaPlayer): Boolean {
        return oldItem == newItem
    }
}