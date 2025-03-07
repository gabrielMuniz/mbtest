package com.muniz.mbtest.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.muniz.mbtest.domain.Exchange
import com.muniz.mbtest.ui.theme.MercadoBitCoinAndroidTheme
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ExchangeListScreen() {

    val viewModel: ExchangeListViewModel = koinViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LaunchedEffect(Unit) {
            viewModel.getExchanges()
        }

        when {
            state.isLoading -> {
                LoadingIndicator()
            }

            state.error != null -> {
                ErrorMessage(state.error)
            }

            else -> {
                ExchangeList(exchanges = state.exchanges)
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
        Text(text = "Erro: $error")
    }
}

@Composable
fun ExchangeList(exchanges: List<Exchange>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(exchanges) { exchange ->
            ExchangeItem(exchange)
        }
    }
}

@Composable
fun ExchangeItem(exchange: Exchange) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Nome: ${exchange.name}", style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Taxa de Câmbio: ${exchange.exchangeId}",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Taxa de Câmbio: ${exchange.volumeOneDayUsd}",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ExchangeListScreenPreview() {
    MercadoBitCoinAndroidTheme {
        ExchangeListScreen()
    }
}