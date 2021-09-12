package com.github.douglasbastos25.movieshowcase.core.preferences

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SharedPreferencesLikeTest {

    private val context = InstrumentationRegistry.getInstrumentation().context
    private val sharedPreferences = SharedPreferencesLike(context)

    companion object {
        const val NOT_FOUND = "Not Found"
        const val FOUND = "Found"
        const val TOGGLE = "Toggle"
    }


    private fun ensureValueDoesNotExist(key: String) {
        context.getSharedPreferences(
            SharedPreferencesLike.PREFERENCE_FILE_KEY,
            Context.MODE_PRIVATE
        ).edit().remove(key).apply()
    }

    private fun ensureValueExists(key: String) {
        context.getSharedPreferences(
            SharedPreferencesLike.PREFERENCE_FILE_KEY,
            Context.MODE_PRIVATE
        ).edit().putBoolean(key, true).apply()
    }


    @Test
    fun returnFalseFromSharedPreferencesIfFileDoesNotExist() {
        ensureValueDoesNotExist(NOT_FOUND)
        val result = sharedPreferences.get(NOT_FOUND)
        assertFalse(result)
    }

    @Test
    fun getValueFromSharedPreferencesFileIfExists() {
        ensureValueExists(FOUND)
        val result = sharedPreferences.get(FOUND)
        assertTrue(result)
    }

    @Test
    fun toggleValueFromTrueToFalse() {
        ensureValueExists(TOGGLE)
        val result = sharedPreferences.toggle(TOGGLE)
        assertFalse(result)
    }

    @Test
    fun toggleValueFromFalseToTrue() {
        ensureValueDoesNotExist(TOGGLE)
        val result = sharedPreferences.toggle(TOGGLE)
        assertTrue(result)
    }

}
