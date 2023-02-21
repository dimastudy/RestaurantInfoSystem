package com.justadroiddev.restrauntapp.data.database

import androidx.room.*

@Dao
interface RestaurantDao {

    @Query("select * from cached_dishes")
    suspend fun getAllDishes() : List<DishEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDish(dishEntity: DishEntity)

    @Query("delete from cached_dishes")
    suspend fun clearDishes()

    @Delete
    suspend fun deleteDish(dishEntity: DishEntity)

}