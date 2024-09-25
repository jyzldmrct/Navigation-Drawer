package ph.edu.auf.navigationdrawerlesson.helpers

import android.content.Context
import android.content.SharedPreferences
import ph.edu.auf.navigationdrawerlesson.NavigationDrawerLessonApplication

object SharedPreferenceManager {
    private const val PREFERENCE_NAME = "MyAppPreference"

    private val sharedPreferences : SharedPreferences by lazy {
        NavigationDrawerLessonApplication.context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun getString(key: String, value: String): String {
        return sharedPreferences.getString(key, value) ?: value
    }

    fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun removeString(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

}