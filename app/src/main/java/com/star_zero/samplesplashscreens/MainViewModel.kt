package com.star_zero.samplesplashscreens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var isReady: Boolean = false
        private set

    init {
        viewModelScope.launch {
            delay(1000) // dummy
            isReady = true
        }
    }
}