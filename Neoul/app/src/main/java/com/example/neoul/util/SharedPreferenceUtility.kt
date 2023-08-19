package com.example.neoul.util

import com.example.neoul.NeoulApplication

fun saveJwt(jwt:String){
    val spf = NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.putString(UserCode.jwt,jwt)
    editor.apply()
}

fun getJwt():String?{
    val spf = NeoulApplication.sSharedPreference
    return spf.getString(UserCode.jwt,null)
}

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

fun saveSignName(SignName: String){
    val spf = NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.putString(UserCode.signInName,SignName)
    editor.apply()
}

fun getSignName():String? = NeoulApplication.sSharedPreference.getString(UserCode.signInName,null)

fun removeSignName(){
    val spf = NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.remove(UserCode.signInName)
    editor.apply()
}

fun saveUserBirth(birth: String){
    val spf = NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.putString(UserCode.birth, birth.toString())
    editor.apply()
}

fun getUserBirth():String? = NeoulApplication.sSharedPreference.getString(UserCode.birth,null)

fun removeUserBirth(){
    val spf = NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.remove(UserCode.birth)
    editor.apply()
}

fun savePhone(phone: String){
    val spf = NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.putString(UserCode.phoneNum, phone.toString())
    editor.apply()
}

fun getPhone():String? = NeoulApplication.sSharedPreference.getString(UserCode.phoneNum,null)

fun removePhone(){
    val spf = NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.remove(UserCode.phoneNum)
    editor.apply()
}


