package com.example.oru_programele.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DayForecast::class, WeekForecast::class], version = 1, exportSchema = false)
abstract class ForecastDatabase: RoomDatabase() {

    abstract fun dayForecastDao(): DayForecastDao

    abstract fun weekForecastDao(): WeekForecastDao

    companion object {
        @Volatile
        private var INSTANCE: ForecastDatabase? = null

        fun getDatabase(context: Context): ForecastDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ForecastDatabase::class.java,
                    "forecast_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}