package com.justadroiddev.restrauntapp.presentation.ui.login

import android.content.SharedPreferences
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.justadroiddev.restrauntapp.domain.LoginConsts
import com.justadroiddev.restrauntapp.domain.UserConsts
import com.justadroiddev.restrauntapp.domain.UserDomain

class AccountsViewModel : ViewModel() {

    private val currentUser = MutableLiveData<UserDomain>()

    fun observeUser(lifecycleOwner: LifecycleOwner, observer: Observer<UserDomain>){
        currentUser.observe(lifecycleOwner, observer)
    }

    fun updateUser() {
        currentUser.value = UserConsts.takeUser()
    }

    init {
        updateUser()
    }

    fun logOut(sharedPreferences: SharedPreferences){
        with(sharedPreferences.edit()){
            putBoolean(LoginConsts.LOGIN_AUTO, false)
            apply()
        }
    }

}