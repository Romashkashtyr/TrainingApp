package com.example.main.ui.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.main.structures.DashboardItem
import com.example.main.databinding.ItemFavoritesBinding
import com.example.main.ui.OnFavoritesTrainingClick
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class FavoriteItemDelegate(
    private val onFavoriteTrainingClick: OnFavoritesTrainingClick
) : AdapterDelegate<List<DashboardItem>>() {
    override fun isForViewType(items: List<DashboardItem>, position: Int): Boolean {
        return items[position] is DashboardItem.FavoritesItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemFavoritesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
         return FavoriteItemViewHolder(binding, onFavoriteTrainingClick)
    }

    override fun onBindViewHolder(
        items: List<DashboardItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as FavoriteItemViewHolder).bind(items[position] as DashboardItem.FavoritesItem)
    }

    inner class FavoriteItemViewHolder(
        private val binding: ItemFavoritesBinding,
        private val onTrainingClick: OnFavoritesTrainingClick
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DashboardItem.FavoritesItem) {
            binding.arrowButton.setOnClickListener {
                onTrainingClick.onFavoriteTrainingClick()
            }
        }
    }

}