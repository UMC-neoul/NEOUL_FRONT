package com.umc.neoul.util

import com.umc.neoul.NeoulApplication

fun saveJwt(jwt:String){
    val spf = com.umc.neoul.NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.putString(UserCode.jwt,jwt)
    editor.apply()
}

fun getJwt():String?{
    val spf = com.umc.neoul.NeoulApplication.sSharedPreference
    return spf.getString(UserCode.jwt,null)
}

fun removeJwt(){
    val spf = com.umc.neoul.NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.remove(UserCode.jwt)
    editor.apply()
}

fun saveRefresh(refresh: String){
    val spf = com.umc.neoul.NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.putString(UserCode.refresh,refresh)
    editor.apply()
}

fun getRefresh():String? = com.umc.neoul.NeoulApplication.sSharedPreference.getString(UserCode.refresh,null)

fun removeRefresh(){
    val spf = com.umc.neoul.NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.remove(UserCode.refresh)
    editor.apply()
}

fun saveUsername(username: String){
    val spf = com.umc.neoul.NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.putString(UserCode.username,username)
    editor.apply()
}

fun getUsername():String? = com.umc.neoul.NeoulApplication.sSharedPreference.getString(UserCode.username,null)

fun removeUsername(){
    val spf = com.umc.neoul.NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.remove(UserCode.username)
    editor.apply()
}

fun saveSignName(SignName: String){
    val spf = com.umc.neoul.NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.putString(UserCode.signInName,SignName)
    editor.apply()
}

fun getSignName():String? = com.umc.neoul.NeoulApplication.sSharedPreference.getString(UserCode.signInName,null)

fun removeSignName(){
    val spf = com.umc.neoul.NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.remove(UserCode.signInName)
    editor.apply()
}

fun saveUserBirth(birth: String){
    val spf = com.umc.neoul.NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.putString(UserCode.birth, birth.toString())
    editor.apply()
}

fun getUserBirth():String? = com.umc.neoul.NeoulApplication.sSharedPreference.getString(UserCode.birth,null)

fun removeUserBirth(){
    val spf = com.umc.neoul.NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.remove(UserCode.birth)
    editor.apply()
}

fun savePhone(phone: String){
    val spf = com.umc.neoul.NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.putString(UserCode.phoneNum, phone.toString())
    editor.apply()
}

fun getPhone():String? = com.umc.neoul.NeoulApplication.sSharedPreference.getString(UserCode.phoneNum,null)

fun removePhone(){
    val spf = com.umc.neoul.NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.remove(UserCode.phoneNum)
    editor.apply()
}

fun savekakaoLogin(status: Boolean){
    val spf = com.umc.neoul.NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.putBoolean(UserCode.kakao, status)
    editor.apply()
}

fun getkakaoLogin():Boolean? = com.umc.neoul.NeoulApplication.sSharedPreference.getBoolean(UserCode.kakao,false)

fun removekakaoLogin(){
    val spf = com.umc.neoul.NeoulApplication.sSharedPreference
    val editor = spf.edit()

    editor.remove(UserCode.kakao)
    editor.apply()
}


