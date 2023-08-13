package com.example.neoul.data.preference

import android.content.Context
import android.content.SharedPreferences
import com.example.neoul.NeoulApplication
import com.example.neoul.util.UserCode.jwt

class ApplicationPreferenceManager(
    private val context: Context
) {
    companion object {
        const val NAME = "NEOUL"
        const val JWT = "jwt"
        const val USERNAME = "username"
        const val REFRESH = "refresh"
    }

    private fun getPreferences(context: Context): SharedPreferences {
        return  context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    private val prefs by lazy { getPreferences(context) }

    private val editor by lazy { prefs.edit() }

    fun putJwt(jwt: String) {
        editor.putString(JWT, jwt)
        editor.apply()
    }

    fun getJwt(): String? {
        return prefs.getString(jwt, null)
    }

}