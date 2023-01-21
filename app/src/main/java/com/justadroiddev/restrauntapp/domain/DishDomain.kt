package com.justadroiddev.restrauntapp.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.justadroiddev.restrauntapp.data.network.DishPunkt
import kotlinx.parcelize.Parcelize

@Parcelize
data class DishDomain(
    val dishName: String,
    val id: Int,
    val ingredients: String,
    val orderedCount: Int,
    val portion: Int,
    val portionVariant: Int,
    val pricePerPortion: Double,
    val rating: Int,
    val category: CategoryDomain? = null,
    val dishImage: String
) : Parcelable {

    private var dishNotes: String = ""

    fun noteToDish(message: String) {
        dishNotes = "$dishNotes\n$message"
    }

    fun takeNote() = dishNotes

    fun mapToDishPunkt() = DishPunkt(portion, id, dishNotes)

    fun portionChange(count: Int) = DishDomain(dishName, id, ingredients, orderedCount, count, portionVariant, pricePerPortion, rating, category, dishImage)


}