package com.example.trainingapp.presentation.main.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.domain.DashboardItem
import com.example.trainingapp.domain.OnClick
import com.example.trainingapp.presentation.main.delegates.StepsItemDelegate
import com.example.trainingapp.presentation.main.delegates.WaterItemDelegate
import com.example.trainingapp.presentation.main.delegates.WorkoutItemDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class DashboardAdapterDelegates(
    private val onClick: OnClick,
    private var items: List<DashboardItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val delegatesManager = AdapterDelegatesManager<List<DashboardItem>>().apply {
        addDelegate(StepsItemDelegate())
        addDelegate(WaterItemDelegate(onClick::onAddWaterClicked))
        addDelegate(WorkoutItemDelegate())
    }


    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(items, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(items, position, holder)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(newItems: List<DashboardItem>) {
        this.items = newItems
        notifyItemChanged(itemCount)
    }
}
