package com.niks.counter.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    val count: Flow<Int>
    val soundEnabled: Flow<Boolean>
    val vibrationEnabled: Flow<Boolean>
    val showButtons: Flow<Boolean>


    suspend fun resetCounter()
    suspend fun saveCounter(value: Int)
    suspend fun saveSoundEnabled(value: Boolean)
    suspend fun saveVibrationEnabled(value: Boolean)
    suspend fun saveShowButtons(value: Boolean)
}