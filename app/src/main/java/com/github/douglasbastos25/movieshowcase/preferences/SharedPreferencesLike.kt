package com.github.douglasbastos25.movieshowcase.preferences

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesLike(context: Context) {
    private var preferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)

    companion object {
        const val PREFERENCE_FILE_KEY = "com.github.douglasbastos25.movieshowcase.LIKE"
        const val LIKE_VALUE = "com.github.douglasbastos25.movieshowcase.VALUE"
    }

    fun get(value: String): Boolean = preferences.getBoolean(value, false)

    private fun put(value: String, like: Boolean): Boolean {
        if (like){
            preferences.edit().putBoolean(value, true).apply()
        } else {
            preferences.edit().remove(value).apply()
        }
        return like
    }

    fun toggle(value: String): Boolean = put(value, !get(value))

}