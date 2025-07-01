package com.niks.counter.domain.repository

interface SoundPlayerRepository {
    fun load()
    fun playSound()
    fun release()

}