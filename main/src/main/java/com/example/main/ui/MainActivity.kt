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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseActivity
import com.example.core.navigation.RouterHolder
import com.example.core.navigation.Screen
import com.example.main.databinding.ActivityMainBinding
import com.example.main.di.MainComponent
import com.example.main.domain.MainRepository
import com.example.main.structures.DashboardItem
import com.example.main.ui.adapters.DashboardAdapterDelegates
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView, OnAddWaterClicked,
    OnTrainingClick, OnViewTrainingsClicked, SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var stepSensor: Sensor? = null

    private var initialSteps = -1f

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

    init {
        MainComponent.getMainInstance().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainPresenter.requestGetScreenData()

        initSensor()
        checkRuntimePermission()
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

    private fun initSensor() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
    }

    companion object {
        fun getIntent(fromContext: Context) = Intent(fromContext, MainActivity::class.java)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER) {
            val totalSteps = event.values[0]

            if (initialSteps < 0) {
                initialSteps = totalSteps
            }

            val currentSteps = (totalSteps - initialSteps).toInt()

            adapterDelegate.updateSteps(currentSteps)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) = Unit


}
