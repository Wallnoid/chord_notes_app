package com.chord_notes_app.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager private constructor(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)

    companion object {
        @Volatile
        private var instance: SharedPreferencesManager? = null

        fun getInstance(context: Context): SharedPreferencesManager {
            return instance ?: synchronized(this) {
                instance ?: SharedPreferencesManager(context).also { instance = it }
            }
        }
    }

    fun saveData(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getData(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue).toString()
    }

    fun clearData() {
        sharedPreferences.edit().clear().apply()
    }
}
