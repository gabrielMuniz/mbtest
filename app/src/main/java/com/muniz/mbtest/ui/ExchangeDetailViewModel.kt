package com.muniz.mbtest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muniz.mbtest.domain.model.ExchangeDetail
import com.muniz.mbtest.domain.repositories.ExchangeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExchangeDetailViewModel(private val repository: ExchangeRepository) : ViewModel() {

    private val _state = MutableStateFlow(ExchangeDetailState())
    val state: StateFlow<ExchangeDetailState> = _state

    fun loadExchange(exchangeId: String) {
        viewModelScope.launch {
            _state.value = ExchangeDetailState(isLoading = true)
            try {
                repository.getExchangeById(exchangeId).collect { exchange ->
                    _state.value = ExchangeDetailState(exchange = exchange)
                }
            } catch (e: Exception) {
                _state.value = ExchangeDetailState(error = e.message)
            }
        }
    }
}

data class ExchangeDetailState(
    val isLoading: Boolean = false,
    val exchange: ExchangeDetail? = null,
    val error: String? = null
)