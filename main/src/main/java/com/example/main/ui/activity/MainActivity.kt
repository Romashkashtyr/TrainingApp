package com.example.main.ui.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseActivity
import com.example.core.data.datastore.StepsDataStore
import com.example.core.navigation.RouterHolder
import com.example.core.navigation.Screen
import com.example.core.utils.getLocalDate
import com.example.main.R
import com.example.main.databinding.ActivityMainBinding
import com.example.main.di.MainComponent
import com.example.main.domain.repository.MainRepository
import com.example.main.domain.repository.StepsRepository
import com.example.main.service.StepsCounterService
import com.example.main.structures.DashboardItem
import com.example.main.ui.activity.MainPresenter
import com.example.main.ui.activity.MainView
import com.example.main.ui.OnAddWaterClicked
import com.example.main.ui.OnFavoritesTrainingClick
import com.example.main.ui.OnTrainingClick
import com.example.main.ui.OnViewTrainingsClicked
import com.example.main.ui.adapters.DashboardAdapterDelegates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView, OnAddWaterClicked,
    OnTrainingClick, OnFavoritesTrainingClick, OnViewTrainingsClicked {

    private lateinit var sensorManager: SensorManager
    private var stepSensor: Sensor? = null


    @Inject
    lateinit var mainRepository: MainRepository

    @Inject
    lateinit var stepsRepository: StepsRepository

    @Inject
    lateinit var stepsDataStore: StepsDataStore


    private val mainPresenter by moxyPresenter {
        MainPresenter(
            mainRepository,
            stepsRepository,
            stepsDataStore
        )
    }
    private lateinit var binding: ActivityMainBinding


    private val items = mutableListOf(
        DashboardItem.StepsItem(5000),
        DashboardItem.WaterItem(1500),
        DashboardItem.WorkoutItem(3),
        DashboardItem.TrainingListItem(),
        DashboardItem.FavoritesItem
    )

    private val adapterDelegate = DashboardAdapterDelegates(
        onAddWaterClicked = this,
        onTrainingClick = this,
        items = items,
        onFavoritesTrainingClick = this
    )


    init {
        MainComponent.getMainInstance().inject(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        refreshFragment()
        mainPresenter.requestGetScreenData()
        mainPresenter.observeSteps(getLocalDate())

        startStepCounterService()

        initSensor()
        checkRuntimePermission()
        restoringSteps()
    }


    override fun onAddWaterClicked(newAmount: Int) {
        mainPresenter.requestAddWater(newAmount)
    }

    override fun onTrainingClick() {
        RouterHolder.router.navigateTo(Screen.TrainingNav(this))
    }


    override fun onViewTrainingsClicked() {
        TODO()
    }

    override fun onFavoriteTrainingClick() {
        binding.dashboardRecyclerView.visibility = View.GONE
        binding.mainFragmentContainer.visibility = View.VISIBLE

        RouterHolder.router.navigateToFragment(
            Screen.TrainingFav(this,
                R.id.mainFragmentContainer
        ),
            supportFragmentManager
        )
    }

    override fun initListData(waterAmount: Int) {
        binding.dashboardRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterDelegate
        }
    }

    private fun initRecycler() {
        binding.dashboardRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterDelegate
        }
    }

    override fun addWater(waterCount: Int) {
        mainPresenter.requestAddWater(waterCount)
    }

    override fun updateSteps(steps: Int) {
        val updatedItems = items.toMutableList()

        val index = updatedItems.indexOfFirst {
            it is DashboardItem.StepsItem
        }

        if (index != -1) {
            updatedItems[index] = DashboardItem.StepsItem(steps)
            adapterDelegate.updateItems(updatedItems)
        }
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
            val savedSteps = withContext(Dispatchers.IO) {
                stepsDataStore.getCurrentSteps()
            }

            withContext(Dispatchers.Main) {
                adapterDelegate.updateSteps(savedSteps)
            }
        }
    }

    private fun startStepCounterService() {

        val intent = StepsCounterService.Companion.getIntentService(this)

        ContextCompat.startForegroundService(
            this,
            intent
        )
    }

    private fun refreshFragment() {
        supportFragmentManager.addOnBackStackChangedListener {
            val hasFragment = supportFragmentManager.backStackEntryCount > 0

            binding.mainFragmentContainer.visibility = if (hasFragment) View.VISIBLE else View.GONE

            binding.dashboardRecyclerView.visibility = if (hasFragment) View.GONE else View.VISIBLE
        }
    }


    private fun initSensor() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
    }



    companion object {
        fun getIntent(fromContext: Context) = Intent(fromContext, MainActivity::class.java)
    }


}