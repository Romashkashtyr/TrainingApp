package com.example.main.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.core.utils.getTodayDate
import com.example.main.R
import com.example.main.di.MainComponent
import com.example.main.domain.repository.StepsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class StepsCounterService : Service(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var stepSensor: Sensor? = null

    @Inject
    lateinit var repository: StepsRepository

    private var initialSteps: Float? = null
    private var lastSavedSteps = -1

    init {
        MainComponent.getMainInstance().inject(this)
    }

    override fun onCreate() {
        super.onCreate()


        createNotificationChannel()

        startForeground(
            1,
            createNotification()
        )

        initSensor()
        restoreInitialSteps()


        startForegroundService()
    }

    private fun restoreInitialSteps() {
        CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
            initialSteps = repository.getInitialSteps()
        }
    }

    private fun registerSensor() {
        stepSensor?.let {
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_UI
            )
        }
    }

    private fun initSensor() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
    }

    private fun startForegroundService() {
        val channelId = "steps_channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Step Counter",
                NotificationManager.IMPORTANCE_LOW
            )

            getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Шаги считаются")
            .setContentText("Фоновый подсчёт шагов активен")
            .setSmallIcon(R.drawable.baseline_steps)
            .build()

        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        registerSensor()
        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onSensorChanged(event: SensorEvent?) {

        if (event?.sensor?.type != Sensor.TYPE_STEP_COUNTER) return

        val totalSteps = event.values[0]

        CoroutineScope(Dispatchers.IO).launch {
            handleSteps(totalSteps)

//            if (initialSteps == null || initialSteps == 0f) {
//                initialSteps = totalSteps
//                repository.saveInitialSteps(totalSteps)
//            }
//
//            val currentSteps = (totalSteps - (initialSteps ?: 0f)).toInt().coerceAtLeast(0)
//            val today = getTodayDate()
//
//            if (System.currentTimeMillis() - lastSavedSteps > 5_000) {
//                repository.save
//            }

//            val savedDate = repository.getDate()
//            var initial = repository.getInitialSteps()

//            if (savedDate != today || initial == null) {
//                initial = totalSteps
//                repository.saveInitialSteps(totalSteps)
//                repository.saveDate(today)
//            }



           // repository.saveSteps(currentSteps)
        }
    }


    private suspend fun handleSteps(totalSteps: Float) {
        val today = getTodayDate()

        if (initialSteps == null) {
            initialSteps = repository.getInitialSteps() ?: totalSteps
            repository.saveInitialSteps(initialSteps!!)
        }

        val currentSteps = (totalSteps - initialSteps!!).toInt().coerceAtLeast(0)

        if (currentSteps == lastSavedSteps) return

        lastSavedSteps = currentSteps

        repository.saveSteps(
            date = today,
            steps = currentSteps
        )
    }



    private fun createNotification(): Notification {
        return NotificationCompat.Builder(
            this,
            CHANNEL_ID
        )
            .setContentTitle("Шагомер работает")
            .setContentText("Подсчет шагов активен")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                CHANNEL_ID,
                "Step Counter",
                NotificationManager.IMPORTANCE_LOW
            )

            val manager = getSystemService(
                NotificationManager::class.java
            )

            manager.createNotificationChannel(channel)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
    }

    companion object {
        fun getIntentService(fromContext: Context) = Intent(fromContext, StepsCounterService::class.java)

        private const val CHANNEL_ID = "step_counter_channel"
    }

}