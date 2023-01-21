package com.justadroiddev.restrauntapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryDomain(
    val id: Int,
    val name: String,
    val categoryImage: String
) : Parcelable{
}