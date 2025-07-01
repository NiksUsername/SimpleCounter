package com.niks.counter.ui.screen

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niks.counter.domain.repository.SoundPlayerRepository
import com.niks.counter.domain.repository.DataStoreRepository
import com.niks.counter.domain.repository.VibratorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CounterViewModel @Inject constructor(
    private val repository: DataStoreRepository,
    private val soundPlayer: SoundPlayerRepository,
    private val vibrator: VibratorRepository
): ViewModel() {

    val count = mutableIntStateOf(0)
    val soundToggle = mutableStateOf(false)
    val showBtnsToggle = mutableStateOf(false)
    val vibrationToggle = mutableStateOf(false)

    init {
        viewModelScope.launch {
            repository.count.collect { count.value = it }
        }
        viewModelScope.launch {
            repository.soundEnabled.collect { soundToggle.value = it }
        }
        viewModelScope.launch {
            repository.showButtons.collect { showBtnsToggle.value = it }
        }
        viewModelScope.launch {
            repository.vibrationEnabled.collect { vibrationToggle.value = it }
        }
    }

    fun reset() {
        viewModelScope.launch {
            count.value = 0
            repository.resetCounter()
        }
    }

    fun increment() {
        viewModelScope.launch {
            count.value += 1
            repository.saveCounter(count.value)
        }
        if (soundToggle.value) {
            soundPlayer.playSound()
        }
        if (vibrationToggle.value) {
            vibrator.vibrate()
        }
    }

    fun decrement() {
        viewModelScope.launch {
            count.value -= 1
            repository.saveCounter(count.value)
        }
        if (soundToggle.value) {
            soundPlayer.playSound()
        }
        if (vibrationToggle.value) {
            vibrator.vibrate()
        }
    }

    fun changeShowButtons() {
        viewModelScope.launch {
            showBtnsToggle.value = !showBtnsToggle.value
            repository.saveShowButtons(showBtnsToggle.value)
        }
    }

    fun changeVibration() {
        viewModelScope.launch {
            vibrationToggle.value = !vibrationToggle.value
            repository.saveVibrationEnabled(vibrationToggle.value)
        }
    }

    fun changeSound() {
        viewModelScope.launch {
            soundToggle.value = !soundToggle.value
            repository.saveSoundEnabled(soundToggle.value)
        }
        if (soundToggle.value) {
            soundPlayer.load()
        }
        else {
            soundPlayer.release()
        }
    }

}