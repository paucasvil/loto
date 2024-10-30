package com.example.loto.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoteriaViewModel:ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    private val _lotoNumbers = mutableStateOf(emptyList<Int>())
    val lotoNumbers: State<List<Int>> = _lotoNumbers

    fun generateLotoNumbers() {
        viewModelScope.launch {
            isLoading = true
            val generatedNumbers = mutableListOf<Int>()
            for (i in 1..6) {
                val number = (1..60).shuffled().first()
                generatedNumbers.add(number)
                _lotoNumbers.value = generatedNumbers.toList()
                delay(2000)
            }
            isLoading = false
        }
    }
}