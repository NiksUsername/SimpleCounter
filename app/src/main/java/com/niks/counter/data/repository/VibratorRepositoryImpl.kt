package com.niks.counter.data.repository

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import com.niks.counter.domain.repository.VibratorRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class VibratorRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : VibratorRepository {
    override fun vibrate() {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(
                VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE)
            )
        } else {
            vibrator.vibrate(50)
        }
    }
}