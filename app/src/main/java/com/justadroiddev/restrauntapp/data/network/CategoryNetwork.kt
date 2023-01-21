package com.justadroiddev.restrauntapp.data.network


import com.google.gson.annotations.SerializedName
import com.justadroiddev.restrauntapp.domain.CategoryDomain

data class CategoryNetwork(
    @SerializedName("CategoryName")
    val categoryName: String,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("ImageURL")
    val imageURL: String
) {

    fun mapToCategoryDomain() : CategoryDomain  =
        CategoryDomain(id, categoryName, imageURL)

}