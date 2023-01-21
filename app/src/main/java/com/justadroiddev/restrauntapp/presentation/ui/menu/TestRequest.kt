package com.justadroiddev.restrauntapp.presentation.ui.menu

import com.justadroiddev.restrauntapp.data.network.RestaurantApi
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val api: RestaurantApi by lazy {
    val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    Retrofit.Builder()
        .baseUrl("https://10.0.2.2:44396")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RestaurantApi::class.java)
}

fun main() {

    val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()


    val request = Request.Builder()
        .get()
        .url("http://127.0.0.1:5000/Menu")
        .build()

    val call = client.newCall(request)

    val response = call.execute()

    if (response.isSuccessful){
        println(response.body?.toString())
    } else {
        println("Error")
    }

}