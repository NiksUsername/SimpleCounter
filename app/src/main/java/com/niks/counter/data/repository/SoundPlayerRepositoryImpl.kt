package com.niks.counter.data.repository

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import com.niks.counter.R
import com.niks.counter.domain.repository.SoundPlayerRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SoundPlayerRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context): SoundPlayerRepository {
    private var soundPool: SoundPool? = null
    private var soundId: Int = 0
    private var loaded = false

    override fun load() {
        if (soundPool != null) return

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()

        soundId = soundPool!!.load(context, R.raw.tick, 1)
        soundPool!!.setOnLoadCompleteListener { _, _, status ->
            loaded = status == 0
        }
    }

    override fun playSound() {
        if (loaded) {
            soundPool?.play(soundId, 1f, 1f, 1, 0, 1f)
        }
    }

    override fun release() {
        soundPool?.release()
        soundPool = null
        loaded = false
    }
}