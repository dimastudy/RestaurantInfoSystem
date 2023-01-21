package com.justadroiddev.restrauntapp.presentation.ui.login

import android.content.SharedPreferences
import androidx.lifecycle.*
import com.justadroiddev.restrauntapp.domain.usecases.RegistrationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase
) : ViewModel() {

    private val resultLiveData = MutableLiveData<Boolean?>()

    fun observeResultData(lifecycleOwner: LifecycleOwner, observer: Observer<Boolean?>){
        resultLiveData.observe(lifecycleOwner, observer)
    }

    fun doneRegistration() {
        resultLiveData.value = null
    }

    fun registerAccount(sharedPreferences: SharedPreferences, firstName: String, lastName: String, mail: String, password: String) = viewModelScope.launch{
        if (!firstName.isNullOrEmpty() && !lastName.isNullOrEmpty() && !mail.isNullOrEmpty() && !password.isNullOrEmpty()) {
            val request = async(Dispatchers.IO) {
                registrationUseCase.invoke(sharedPreferences, firstName, lastName, mail, password)
            }
            resultLiveData.value = request.await()
        } else {
            resultLiveData.value = false
        }
    }

}