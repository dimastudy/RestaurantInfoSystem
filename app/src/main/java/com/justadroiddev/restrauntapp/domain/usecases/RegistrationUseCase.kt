package com.justadroiddev.restrauntapp.domain.usecases

import android.content.SharedPreferences
import android.util.Log
import com.justadroiddev.restrauntapp.domain.LoginConsts
import com.justadroiddev.restrauntapp.domain.Repository
import com.justadroiddev.restrauntapp.domain.UserDomain
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(
        sharedPreferences: SharedPreferences,
        firstName: String,
        lastName: String,
        mail: String,
        password: String
    ) : Boolean{
        val result = repository.registerNewUser(UserDomain(firstName, lastName, mail, password))
        with(sharedPreferences.edit()) {
            if (result) {
                putString(LoginConsts.LOGIN_MAIL, mail)
                putString(LoginConsts.LOGIN_PASSWORD, password)
                apply()
            }
        }
        return result
    }

}