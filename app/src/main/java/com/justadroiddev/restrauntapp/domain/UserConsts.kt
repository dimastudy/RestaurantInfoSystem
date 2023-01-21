package com.justadroiddev.restrauntapp.domain

object UserConsts {

    private var userId: Int? = null

    private var currentUser: UserDomain? = null

    fun updateUserId(id: Int){
        userId = id
    }

    fun updateUser(userDomain: UserDomain) {
        currentUser = userDomain
    }

    fun takeUser() = currentUser

    fun takeId() = userId



}