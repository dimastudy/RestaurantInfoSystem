package com.justadroiddev.restrauntapp.domain.usecases

import android.content.SharedPreferences
import com.justadroiddev.restrauntapp.domain.*
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(sharedPreferences: SharedPreferences, email: String, password: String) : ResultDomain<UserDomain> {

        val result = repository.loginToAccount(UserDomain("","",email, password))
        val userData = when(result) {
            is ResultDomain.Success -> result.data
            is ResultDomain.Error -> null
        }
        with(sharedPreferences.edit()){
            if (userData != null){
                putString(LoginConsts.LOGIN_MAIL, userData.email)
                putString(LoginConsts.LOGIN_PASSWORD, userData.password)
                putInt(LoginConsts.LOGIN_ID, userData.takeId()!!)
                putBoolean(LoginConsts.LOGIN_AUTO, true)
                apply()
                UserConsts.updateUserId(userData.takeId()!!)
                UserConsts.updateUser(userData)
            }
        }
        return result
    }

}