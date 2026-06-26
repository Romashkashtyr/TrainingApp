package com.example.database.models.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.models.main_modules.StepsDb
import kotlinx.coroutines.flow.Flow


@Dao
interface MainDao {

    @Query("SELECT steps FROM steps_history WHERE date = :date")
    fun observeTodaySteps(date: String): Flow<Int?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(entity: StepsDb)


    @Query("SELECT * FROM steps_history ORDER BY date DESC")
    fun observeStepsHistory(): Flow<List<StepsDb>>


    @Query("""SELECT * FROM steps_history WHERE date = :date""")
    suspend fun getByDate(date: String): StepsDb?
}
