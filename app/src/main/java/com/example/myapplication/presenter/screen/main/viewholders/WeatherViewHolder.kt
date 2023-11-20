package com.example.myapplication.presenter.screen.main.viewholders

import android.annotation.SuppressLint
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.R
import com.example.myapplication.core.adapter.viewHolder
import com.example.myapplication.databinding.ItemWeatherBinding

@Entity(tableName = "weather_table")
data class Weather(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "cloud") var cloud: String,
    @ColumnInfo(name = "humidity") var humidity: String,
    @ColumnInfo(name = "pressure") var pressure: String,
    @ColumnInfo(name = "temperatureMax") var temperatureMax: String,
    @ColumnInfo(name = "temperatureMin") var temperatureMin: String,
    @ColumnInfo(name = "speed") var speed: String
)

@SuppressLint("SetTextI18n")
fun weatherViewHolder(
    click: (Long) -> Unit
) = viewHolder<Weather>(R.layout.item_weather) { weather ->
    with(ItemWeatherBinding.bind(this)) {
        tvDateInfo.text = weather.date
        tvCloudInfo.text = "${weather.cloud}${"%"}"
        tvPressureInfo.text = weather.pressure
        tvHumidityInfo.text = weather.humidity
        tvTemperatureMaxInfo.text = weather.temperatureMax
        tvTemperatureMinInfo.text = weather.temperatureMin
        tvSpeedInfo.text = weather.speed
        root.setOnClickListener { click(weather.id) }
    }
}