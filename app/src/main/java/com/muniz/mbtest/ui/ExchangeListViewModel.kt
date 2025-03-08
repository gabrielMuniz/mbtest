package com.muniz.mbtest.ui

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
                    _state.value = ExchangeListState(
                        allExchanges = exchanges,
                        visibleExchanges = exchanges.take(_state.value.pageSize)
                    )
                }
            } catch (e: Exception) {
                _state.value = ExchangeListState(error = e.message)
            }
        }
    }

    fun loadMoreExchanges() {
        if (_state.value.isLoadingMore) return

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoadingMore = true)

            val nextPage = _state.value.currentPage + 1
            val startIndex = (nextPage - 1) * _state.value.pageSize
            val endIndex = startIndex + _state.value.pageSize
            val newItems = _state.value.allExchanges.subList(
                startIndex, endIndex.coerceAtMost(_state.value.allExchanges.size)
            )

            if (newItems.isNotEmpty()) {
                _state.value = _state.value.copy(
                    visibleExchanges = _state.value.visibleExchanges + newItems,
                    currentPage = nextPage,
                    isLoadingMore = false
                )
            } else {
                _state.value = _state.value.copy(isLoadingMore = false)
            }
        }
    }

}

data class ExchangeListState(
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val allExchanges: List<Exchange> = emptyList(),
    val visibleExchanges: List<Exchange> = emptyList(),
    val error: String? = null,
    val currentPage: Int = 1,
    val pageSize: Int = 20
)
