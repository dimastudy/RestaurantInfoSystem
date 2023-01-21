package com.justadroiddev.restrauntapp.presentation.ui.login

import android.content.SharedPreferences
import androidx.lifecycle.*
import com.justadroiddev.restrauntapp.domain.ResultDomain
import com.justadroiddev.restrauntapp.domain.UserDomain
import com.justadroiddev.restrauntapp.domain.usecases.GetUserDataUseCase
import com.justadroiddev.restrauntapp.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val loginLiveData = MutableLiveData<ResultDomain<UserDomain>>()
    private val userDataLiveData = MutableLiveData<Triple<String, String, Boolean>?>()

    fun login(sharedPreferences: SharedPreferences ,mail: String, password: String) = viewModelScope.launch {
        val request = async(Dispatchers.IO) {
            loginUseCase.invoke(sharedPreferences, mail, password)
        }
        loginLiveData.value = request.await()
    }

    init {

    }

    fun updateUserData(sharedPreferences: SharedPreferences) {
        userDataLiveData.value = getUserDataUseCase.invoke(sharedPreferences)
    }

    fun observeUserData(lifecycleOwner: LifecycleOwner, observer: Observer<Triple<String, String, Boolean>?>){
        userDataLiveData.observe(lifecycleOwner, observer)
    }

    fun observeLoginData(lifecycleOwner: LifecycleOwner, observer: Observer<ResultDomain<UserDomain>>){
        loginLiveData.observe(lifecycleOwner, observer)
    }


}