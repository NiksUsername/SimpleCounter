package com.niks.counter.domain.model

import androidx.lifecycle.ViewModel
import com.niks.counter.data.repository.CounterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CounterViewModel @Inject constructor(
    private val repository: CounterRepository
): ViewModel() {

}