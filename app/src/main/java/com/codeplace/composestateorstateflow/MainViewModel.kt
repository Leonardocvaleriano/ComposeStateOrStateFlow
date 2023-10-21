package com.codeplace.composestateorstateflow

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class MainViewModel() : ViewModel() {


    // StateFlow
    val _isUserFirstAccess = MutableStateFlow(true)
    val isUserFirstAccess = _isUserFirstAccess.asStateFlow()

    // Compose State
    var composeStateIsUserFirstAccess by mutableStateOf(true)
        private set


    fun recognizeFirstUserAccess() {
        // StateFlow
        _isUserFirstAccess.value = false

        //Compose State
        composeStateIsUserFirstAccess = false

    }

}