package com.example.myapplication.domain.repositories

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.presenter.screen.main.viewholders.Weather

@Dao
interface WeatherDao {

    @Query("Select  * from weather_table order by id ASC")
    fun getAll(): LiveData<List<Weather>>

    @Query("SELECT * FROM weather_table WHERE id = :id")
    fun get(id: Long): LiveData<Weather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<Weather>)

}