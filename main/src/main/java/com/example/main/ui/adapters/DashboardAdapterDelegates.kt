package com.example.main.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.structures.DashboardItem
import com.example.main.ui.delegates.StepsItemDelegate
import com.example.main.ui.delegates.TrainingListItemDelegate
import com.example.main.ui.delegates.WaterItemDelegate
import com.example.main.ui.delegates.WorkoutItemDelegate
import com.example.trainings.domain.events.OnAddWaterClicked
import com.example.trainings.domain.events.OnTrainingClick
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class DashboardAdapterDelegates(
    private val onAddWaterClicked: OnAddWaterClicked,
    private val onTrainingClick: OnTrainingClick,
    private var items: List<DashboardItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val delegatesManager = AdapterDelegatesManager<List<DashboardItem>>().apply {
        addDelegate(StepsItemDelegate())
        addDelegate(WaterItemDelegate(onAddWaterClicked))
        addDelegate(WorkoutItemDelegate())
        addDelegate(TrainingListItemDelegate(onTrainingClick))
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
