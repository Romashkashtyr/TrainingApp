package com.example.main.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseActivity
import com.example.core.data.datastore.StepsDataStore
import com.example.core.navigation.RouterHolder
import com.example.core.navigation.Screen
import com.example.main.databinding.ActivityMainBinding
import com.example.main.di.MainComponent
import com.example.main.domain.MainRepository
import com.example.main.structures.DashboardItem
import com.example.main.ui.adapters.DashboardAdapterDelegates
import kotlinx.coroutines.launch
import moxy.ktx.moxyPresenter
import java.time.LocalDate
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView, OnAddWaterClicked,
    OnTrainingClick, OnViewTrainingsClicked, SensorEventListener {

        private lateinit var stepsDataStore: StepsDataStore

    private lateinit var sensorManager: SensorManager
    private var stepSensor: Sensor? = null


    @Inject
    lateinit var mainRepository: MainRepository


    private val mainPresenter by moxyPresenter { MainPresenter(mainRepository) }
    private lateinit var binding: ActivityMainBinding


    private val items = mutableListOf(
        DashboardItem.StepsItem(5000),
        DashboardItem.WaterItem(1500),
        DashboardItem.WorkoutItem(3),
        DashboardItem.TrainingListItem()
    )

    private val adapterDelegate = DashboardAdapterDelegates(
        onAddWaterClicked = this,
        onTrainingClick = this,
        items = items
    )


    init {
        MainComponent.getMainInstance().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainPresenter.requestGetScreenData()
        stepsDataStore = StepsDataStore(this)

        restoringSteps()

        initSensor()
        checkRuntimePermission()
    }


    override fun onResume() {
        super.onResume()

        stepSensor?.also {
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_UI
            )
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }


    override fun onAddWaterClicked(newAmount: Int) {
        mainPresenter.requestAddWater(newAmount)
    }


    override fun onViewTrainingsClicked() {
        TODO()
    }

    override fun onTrainingClick() {
        RouterHolder.router.navigateTo(Screen.TrainingNav(this))
    }

    override fun initListData(waterAmount: Int) {
        binding.dashboardRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterDelegate
        }
    }

    override fun addWater(waterCount: Int) {
        mainPresenter.requestAddWater(waterCount)
    }


    private fun checkRuntimePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACTIVITY_RECOGNITION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACTIVITY_RECOGNITION),
                    100
                )
            }
        }
    }

    private fun restoringSteps() {
        lifecycleScope.launch {
            val savedSteps = stepsDataStore.getCurrentSteps()

            adapterDelegate.updateSteps(savedSteps)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun getTodayDate(): String {
        return LocalDate.now().toString()
    }

    private fun initSensor() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
    }

    companion object {
        fun getIntent(fromContext: Context) = Intent(fromContext, MainActivity::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type != Sensor.TYPE_STEP_COUNTER) return

        val totalSteps = event.values[0]

        lifecycleScope.launch {

            val today = getTodayDate()

            val savedDate = stepsDataStore.getDate()
            var savedInitial = stepsDataStore.getInitialSteps()

            if (savedDate != today || savedInitial == null) {

                savedInitial = totalSteps

                stepsDataStore.saveInitialSteps(totalSteps)
                stepsDataStore.saveDate(today)
            }

            val currentSteps = (totalSteps - savedInitial).toInt()

            val safeSteps = if (currentSteps < 0) 0 else currentSteps

            stepsDataStore.saveCurrentSteps(safeSteps)

            adapterDelegate.updateSteps(safeSteps)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) = Unit


}
