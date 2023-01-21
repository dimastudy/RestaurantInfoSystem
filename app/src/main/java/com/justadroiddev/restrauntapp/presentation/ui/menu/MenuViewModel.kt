package com.justadroiddev.restrauntapp.presentation.ui.menu

import androidx.lifecycle.*
import com.justadroiddev.restrauntapp.data.network.RestaurantApi
import com.justadroiddev.restrauntapp.domain.CategoryDomain
import com.justadroiddev.restrauntapp.domain.DishDomain
import com.justadroiddev.restrauntapp.domain.Repository
import com.justadroiddev.restrauntapp.domain.ResultDomain
import com.justadroiddev.restrauntapp.domain.usecases.GetDishByCategoryUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


class MenuViewModel @AssistedInject constructor(
    @Assisted private val category: CategoryDomain,
    private val getDishByCategoryUseCase: GetDishByCategoryUseCase
) : ViewModel() {

    private val menuLiveData = MutableLiveData<ResultDomain<List<DishDomain>>>()

    fun observeMenu(owner: LifecycleOwner, observer: Observer<ResultDomain<List<DishDomain>>>) {
        menuLiveData.observe(owner, observer)
    }

    init {
        initListDishes()
    }

    fun initListDishes() = viewModelScope.launch {
        val resultRequest = async(Dispatchers.IO) {
            getDishByCategoryUseCase.invoke(category.id)
        }
        val result = resultRequest.await()
        menuLiveData.value = result
    }

    companion object {
        fun getViewModel(
            category: CategoryDomain,
            factory: AssistedFactoryMenu
        ) : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(MenuViewModel::class.java)){
                    return factory.getViewModel(category) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactoryMenu {
        fun getViewModel(category: CategoryDomain): MenuViewModel
    }


}

