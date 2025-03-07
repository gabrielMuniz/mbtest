package com.muniz.mbtest.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muniz.mbtest.domain.Exchange
import com.muniz.mbtest.domain.repositories.ExchangeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExchangeListViewModel(
    private val repository: ExchangeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ExchangeListState())
    val state: StateFlow<ExchangeListState> = _state

    fun getExchanges() {
        viewModelScope.launch {
            _state.value = ExchangeListState(isLoading = true)
            try {
                repository.getExchanges().collect { exchanges ->
                    _state.value = ExchangeListState(exchanges = exchanges)
                }
            } catch (e: Exception) {
                _state.value = ExchangeListState(error = e.message)
            }
        }
    }

}


data class ExchangeListState(
    val isLoading: Boolean = false,
    val exchanges: List<Exchange> = emptyList(),
    val error: String? = null
)