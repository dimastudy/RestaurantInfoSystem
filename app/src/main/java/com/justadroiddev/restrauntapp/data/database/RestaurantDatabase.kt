package com.justadroiddev.restrauntapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DishEntity::class], version = 1, exportSchema = false)
abstract class RestaurantDatabase : RoomDatabase() {

    abstract fun dao(): RestaurantDao

    companion object {
        lateinit var INSTANCE: RestaurantDatabase
        fun takeDatabase(context: Context): RestaurantDatabase {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    RestaurantDatabase::class.java,
                    "restaurantDb"
                ).build()
            }
            return INSTANCE
        }
    }
}