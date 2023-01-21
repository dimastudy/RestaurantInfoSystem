package com.justadroiddev.restrauntapp.data

import android.util.Log
import com.justadroiddev.restrauntapp.data.network.CloudDataSource
import com.justadroiddev.restrauntapp.data.network.DishPunkt
import com.justadroiddev.restrauntapp.domain.*
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val cloudDataSource: CloudDataSource
) : Repository {
    override suspend fun getDishesByCategory(categoryId: Int): ResultDomain<List<DishDomain>> {
        return try {
            val data = cloudDataSource.fetchDishes().map {
                it.mapToDishDomain()
            }.filter {
                it.category?.id == categoryId
            }
            ResultDomain.Success(data)
        } catch (e: Exception){
            ResultDomain.Error("Something went wrong")
        }
    }

    override suspend fun getCategories(): ResultDomain<List<CategoryDomain>> {
        return try {
            val data = cloudDataSource.fetchCategories().map {
                Log.e("Repo", it.categoryName)
                it.mapToCategoryDomain()
            }
            ResultDomain.Success(data)
        } catch (e: Exception){
            ResultDomain.Error("Something went wrong")
        }
    }

    override suspend fun registerNewUser(user: UserDomain) : Boolean{
         return try {
            cloudDataSource.registerNewUser(user)
             true
        } catch (e: Exception){
            false
        }

    }

    override suspend fun loginToAccount(user: UserDomain): ResultDomain<UserDomain> {
        return try {
            ResultDomain.Success(cloudDataSource.loginToAccount(user).mapToUserDomain())
        } catch (e: Exception){
            ResultDomain.Error("Something went wrong")
        }
    }

    override suspend fun makeAnOrder(
        clientId: Int,
        address: String,
        deliveryDate: String,
        isDelivery: Boolean,
        tableNumber: Int,
        listDishes: List<DishDomain>
    ) : Boolean{
        val orderedDishes: List<DishPunkt> = listDishes.map {
            it.mapToDishPunkt()
        }
        val order = OrderDomain(address, clientId, deliveryDate, orderedDishes, isDelivery, tableNumber)
        return try {
            cloudDataSource.makeAnOrder(order)
            true
        } catch (e: Exception){
            false
        }
    }

    override suspend fun getOrdersByClient(idClient: Int): ResultDomain<List<OrderResponseDomain>> {
        return try {
            val data = cloudDataSource.getAllOrdersOfClient(idClient).map {
                it.mapToOrderResponseDomain()
            }
            ResultDomain.Success(data)
        } catch (e: Exception){
            ResultDomain.Error("Виникла помилка! Спробуйте пізніше")
        }
    }


}