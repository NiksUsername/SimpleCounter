package com.niks.counter.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.niks.counter.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("settings")

class DataStoreRepositoryImpl(private val context: Context): DataStoreRepository {
    companion object {
        val COUNTER_KEY = intPreferencesKey("counter")
        val SOUND_KEY = booleanPreferencesKey("sound")
        val VIBRATION_KEY = booleanPreferencesKey("vibration")
        val SHOW_BUTTONS_KEY = booleanPreferencesKey("show_buttons")
    }


    override val count: Flow<Int> = context.dataStore.data.map{it[COUNTER_KEY] ?: 0}
    override val soundEnabled: Flow<Boolean> = context.dataStore.data.map{it[SOUND_KEY] ?: false}
    override val vibrationEnabled: Flow<Boolean> = context.dataStore.data.map{it[VIBRATION_KEY] ?: false}
    override val showButtons: Flow<Boolean> = context.dataStore.data.map{it[SHOW_BUTTONS_KEY] ?: false}

    override suspend fun resetCounter() {
        context.dataStore.edit { it[COUNTER_KEY] = 0 }
    }

    override suspend fun saveCounter(value: Int) {
        context.dataStore.edit { it[COUNTER_KEY] = value }
    }

    override suspend fun saveSoundEnabled(value: Boolean) {
        context.dataStore.edit { it[SOUND_KEY] = value }
    }

    override suspend fun saveShowButtons(value: Boolean) {
        context.dataStore.edit { it[SHOW_BUTTONS_KEY] = value }
    }

    override suspend fun saveVibrationEnabled(value: Boolean) {
        context.dataStore.edit { it[VIBRATION_KEY] = value }
    }

}