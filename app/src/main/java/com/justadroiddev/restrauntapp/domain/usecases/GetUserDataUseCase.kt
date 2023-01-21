package com.justadroiddev.restrauntapp.domain.usecases

import android.content.SharedPreferences
import com.justadroiddev.restrauntapp.domain.LoginConsts
import javax.inject.Inject


class GetUserDataUseCase @Inject constructor(){

    operator fun invoke(sharedPreferences: SharedPreferences) : Triple<String, String, Boolean>?{
        val mail = sharedPreferences.getString(LoginConsts.LOGIN_MAIL, null)
        val password = sharedPreferences.getString(LoginConsts.LOGIN_PASSWORD, null)
        val auto = sharedPreferences.getBoolean(LoginConsts.LOGIN_AUTO, false)
        return if (!mail.isNullOrEmpty() && !password.isNullOrEmpty()){
            Triple(mail, password, auto)
        } else {
            null
        }
    }

}