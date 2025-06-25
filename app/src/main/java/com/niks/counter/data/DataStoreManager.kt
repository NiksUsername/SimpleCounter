package com.niks.counter.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

private val Context.dataStore by preferencesDataStore("settings")

class DataStoreManager(private val context: Context) {
    companion object {
        val COUNTER_KEY = intPreferencesKey("counter")
        val SOUND_KEY = booleanPreferencesKey("sound")
        val VIBRATION_KEY = booleanPreferencesKey("vibration")
        val SHOW_BUTTONS_KEY = booleanPreferencesKey("show_buttons")
    }


    val count: Flow<Int> = context.dataStore.data.map{it[COUNTER_KEY] ?: 0}
    val soundEnabled: Flow<Boolean> = context.dataStore.data.map{it[SOUND_KEY] ?: false}
    val vibrationEnabled: Flow<Boolean> = context.dataStore.data.map{it[VIBRATION_KEY] ?: false}
    val showButtons: Flow<Boolean> = context.dataStore.data.map{it[SHOW_BUTTONS_KEY] ?: false}

    suspend fun resetCounter() {
        context.dataStore.edit { it[COUNTER_KEY] = 0 }
    }

    suspend fun saveCounter(value: Int) {
        context.dataStore.edit { it[COUNTER_KEY] = value }
    }

    suspend fun saveSoundEnabled(value: Boolean) {
        context.dataStore.edit { it[SOUND_KEY] = value }
    }

    suspend fun saveShowButtons(value: Boolean) {
        context.dataStore.edit { it[SHOW_BUTTONS_KEY] = value }
    }

    suspend fun saveVibrationEnabled(value: Boolean) {
        context.dataStore.edit { it[VIBRATION_KEY] = value }
    }


}