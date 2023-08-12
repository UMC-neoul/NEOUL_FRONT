package com.example.neoul.util

import com.example.neoul.NeoulApplication

fun saveJwt(jwt:String){
    val spf = NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.putString(UserCode.jwt,jwt)
    editor.apply()
}

fun getJwt():String?=NeoulApplication.sSharedPreference.getString(UserCode.jwt,null)

fun removeJwt(){
    val spf = NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.remove(UserCode.jwt)
    editor.apply()
}

fun saveRefresh(refresh: String){
    val spf = NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.putString(UserCode.refresh,refresh)
    editor.apply()
}

fun getRefresh():String? = NeoulApplication.sSharedPreference.getString(UserCode.refresh,null)

fun removeRefresh(){
    val spf = NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.remove(UserCode.refresh)
    editor.apply()
}

fun saveUsername(username: String){
    val spf = NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.putString(UserCode.username,username)
    editor.apply()
}

fun getUsername():String? = NeoulApplication.sSharedPreference.getString(UserCode.username,null)

fun removeUsername(){
    val spf = NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.remove(UserCode.username)
    editor.apply()
}
