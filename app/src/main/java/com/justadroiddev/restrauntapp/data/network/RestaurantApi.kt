package com.justadroiddev.restrauntapp.data.network

import okhttp3.OkHttpClient
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

interface RestaurantApi {

    @GET("Menu")
    suspend fun getMenuList() : List<DishNetwork>

    @GET("Menu/Categories")
    suspend fun getCategoryList() : List<CategoryNetwork>

    @POST("Registration")
    suspend fun registerNewUser(@Body newUser: RegistrationRequestBody)

    @POST("Login")
    suspend fun loginIntoAccount(@Body account: LoginRequestBody) : LoginResponseBody

    @POST("ClientOrders")
    suspend fun makeAnOrder(@Body order: OrderNetwork)

    @GET("ClientOrders/Orders")
    suspend fun getOrdersByClient(@Query("id") clientId: Int) : List<OrdersResponseItem>

}

fun OkHttpClient.Builder.ignoreAllSSLErrors(): OkHttpClient.Builder {
    val naiveTrustManager = object : X509TrustManager {
        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) = Unit
        override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) = Unit
    }

    val insecureSocketFactory = SSLContext.getInstance("TLSv1.2").apply {
        val trustAllCerts = arrayOf<TrustManager>(naiveTrustManager)
        init(null, trustAllCerts, SecureRandom())
    }.socketFactory

    sslSocketFactory(insecureSocketFactory, naiveTrustManager)
    hostnameVerifier(HostnameVerifier { _, _ -> true })
    return this
}