package com.example.oru_programele.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.oru_programele.Converters
import com.example.oru_programele.database.models.DayForecast

@Database(entities = [DayForecast::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ForecastDatabase: RoomDatabase() {

    abstract fun dayForecastDao(): DayForecastDao

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