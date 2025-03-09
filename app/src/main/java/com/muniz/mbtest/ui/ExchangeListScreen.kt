package com.muniz.mbtest.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.muniz.mbtest.R
import com.muniz.mbtest.domain.model.Exchange
import com.muniz.mbtest.ui.extensions.formatDecimalPlaces
import com.muniz.mbtest.ui.theme.MercadoBitCoinAndroidTheme
import org.koin.androidx.compose.koinViewModel

/*
    Suppress deprecation because the official
    implementation of pull to refresh have issues
 */
@Suppress("DEPRECATION")
@Composable
fun ExchangeListScreen(onExchangeListClicked: (String) -> Unit) {
    val viewModel: ExchangeListViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val isLoaded = rememberSaveable { mutableStateOf(false) }
    val isRefreshing = rememberSaveable { mutableStateOf(false) }

    fun refreshData() {
        isRefreshing.value = true
        viewModel.getExchanges()
        isRefreshing.value = false
    }

    LaunchedEffect(Unit) {
        if (!isLoaded.value) {
            viewModel.getExchanges()
            isLoaded.value = true
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing.value),
            onRefresh = { refreshData() }
        ) {
            when {
                state.isLoading -> {
                    LoadingIndicator()
                }

                state.error != null -> {
                    ErrorMessage(state.error)
                }

                else -> {
                    ExchangeList(
                        state = state,
                        loadMoreExchanges = { viewModel.loadMoreExchanges() },
                        onExchangeClicked = { onExchangeListClicked(it) },
                        paddingValues = innerPadding
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorMessage(error: String?) {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.error_message, error.orEmpty()),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun ExchangeList(
    state: ExchangeListState,
    onExchangeClicked: (String) -> Unit,
    paddingValues: PaddingValues,
    loadMoreExchanges: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
            .consumeWindowInsets(paddingValues = paddingValues)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            items(state.visibleExchanges) { exchange ->
                ExchangeItem(exchange, onExchangeClicked = { onExchangeClicked(it) })
            }

            item {
                if (state.isLoadingMore) {
                    CircularProgressIndicator(modifier = Modifier.fillMaxWidth())
                } else if (state.visibleExchanges.size < state.allExchanges.size) {
                    Button(
                        onClick = { loadMoreExchanges() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(id = R.string.load_more_button))
                    }
                }
            }
        }
    }
}

@Composable
fun ExchangeItem(exchange: Exchange, onExchangeClicked: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onExchangeClicked(exchange.exchangeId) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "${stringResource(id = R.string.name_label)}: ${exchange.name}",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "${stringResource(id = R.string.exchange_id_label)}: ${exchange.exchangeId}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "${
                    stringResource(id = R.string.volume_one_day_usd_label)
                }: ${exchange.volumeOneDayUsd.formatDecimalPlaces()}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ExchangeListScreenPreview() {
    MercadoBitCoinAndroidTheme {
        ExchangeListScreen(onExchangeListClicked = { })
    }
}