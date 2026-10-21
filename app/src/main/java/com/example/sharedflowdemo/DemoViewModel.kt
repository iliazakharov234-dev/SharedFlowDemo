package com.example.sharedflowdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class DemoViewModel : ViewModel() {
    // Приватный поток, в который мы будем "эмитить" (отправлять) данные
    private val _sharedFlow = MutableSharedFlow<Int>()

    // Публичный поток, который будет слушать UI (доступен только для чтения)
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        sharedFlowInit()
    }

    private fun sharedFlowInit() {
        // viewModelScope автоматически отменит корутину, когда ViewModel будет уничтожена
        viewModelScope.launch {
            for (i in 1..1000) {
                delay(2000) // Ждем 2 секунды
                println("Emitting $i") // Вывод в Logcat
                _sharedFlow.emit(i) // Отправляем значение в поток
            }
        }
    }
}