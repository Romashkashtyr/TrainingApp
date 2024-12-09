package com.example.trainingapp.presentation.base.main_logic.rc_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.R
import com.example.trainingapp.data.WaterIntake
import com.example.trainingapp.databinding.ItemStepsBinding
import com.example.trainingapp.databinding.ItemTrainingListBinding
import com.example.trainingapp.databinding.ItemWaterBinding
import com.example.trainingapp.databinding.ItemWorkoutBinding
import com.example.trainingapp.domain.DashboardItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlin.concurrent.thread

class DashboardAdapter(
    private val onClick: OnClick,
    private val listViewData: ArrayList<DashboardItem>
) : RecyclerView.Adapter<DashboardViewHolder>() {


    override fun getItemViewType(position: Int): Int = when (listViewData[position]) {
        is DashboardItem.StepsItem -> R.layout.item_steps
        is DashboardItem.WaterItem -> R.layout.item_water
        is DashboardItem.WorkoutItem -> R.layout.item_workout
        is DashboardItem.TrainingListItem -> R.layout.item_training_list
    }

    override fun getItemCount(): Int {
        return listViewData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_steps -> DashboardViewHolder.StepsViewHolder(
                ItemStepsBinding.inflate(inflater, parent, false)
            )

            R.layout.item_water -> DashboardViewHolder.WaterViewHolder(
                ItemWaterBinding.inflate(inflater, parent, false)
            ) { onClick.onAddWaterClicked() }

            R.layout.item_workout -> DashboardViewHolder.WorkoutsViewHolder(
                ItemWorkoutBinding.inflate(inflater, parent, false)
            )

            R.layout.item_training_list -> DashboardViewHolder.TrainingListViewHolder(
                ItemTrainingListBinding.inflate(inflater, parent, false)
            ) { onClick.onViewTrainingsClicked() }

            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.bind(listViewData[position])
    }

    fun onAddWaterClicked(amount: Int){
        val currentUser = FirebaseAuth.getInstance().currentUser
        currentUser?.let { user ->
            val waterIntakeRef = FirebaseDatabase.getInstance()
                .reference
                .child("get_water")
                .child(user.uid)
                .push()

            val waterIntakeData = DashboardItem.WaterItem(amount)

            waterIntakeRef.setValue(waterIntakeData)
                .addOnSuccessListener {
                    this.
                }

        }
    }

    fun updateWaterLevel(water: DashboardItem.WaterItem){
        listViewData.add(water)
        notifyItemInserted(listViewData.size - 1)
    }

    interface OnClick {
        fun onAddWaterClicked()
        fun onViewTrainingsClicked()
    }
}
