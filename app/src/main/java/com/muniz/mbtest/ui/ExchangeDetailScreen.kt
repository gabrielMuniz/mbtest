package com.muniz.mbtest.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muniz.mbtest.R
import com.muniz.mbtest.ui.extensions.formatDecimalPlaces
import com.muniz.mbtest.ui.extensions.toFormattedDate
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeDetailScreen(
    exchangeId: String,
    onBackClick: () -> Unit
) {

    val viewModel: ExchangeDetailViewModel = koinViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(exchangeId) {
        viewModel.loadExchange(exchangeId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Exchange Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = stringResource(
                                id = R.string.exchange_detail_back_button_description
                            )
                        )
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            when {
                state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                state.error != null -> Text(
                    text = state.error,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )

                state.exchange != null -> {
                    val exchange = state.exchange
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        with(exchange) {
                            DetailItem(
                                label = stringResource(
                                    id = R.string.exchange_detail_name_label
                                ),
                                value = name
                            )
                            DetailItem(
                                label = stringResource(
                                    id = R.string.exchange_detail_exchange_id_label
                                ),
                                value = exchangeId
                            )
                            DetailItem(
                                label = stringResource(
                                    id = R.string.exchange_detail_website_label
                                ),
                                value = website
                            )
                            DetailItem(
                                label = stringResource(
                                    id = R.string.exchange_detail_data_start_label
                                ),
                                value = dataStart?.toFormattedDate()
                            )
                            DetailItem(
                                label = stringResource(
                                    id = R.string.exchange_detail_data_end_label
                                ),
                                value = dataEnd?.toFormattedDate()
                            )
                            DetailItem(
                                label = stringResource(
                                    id = R.string.exchange_detail_data_quote_start_label
                                ),
                                value = dataQuoteStart?.toFormattedDate()
                            )
                            DetailItem(
                                label = stringResource(
                                    id = R.string.exchange_detail_data_orderbook_start_label
                                ),
                                value = dataOrderBookStart?.toFormattedDate()
                            )
                            DetailItem(
                                label = stringResource(
                                    id = R.string.exchange_detail_data_trade_start_label
                                ),
                                value = dataTradeStart?.toFormattedDate()
                            )
                            DetailItem(
                                label = stringResource(
                                    id = R.string.exchange_detail_data_trade_end_label
                                ),
                                value = dataTradeEnd?.toFormattedDate()
                            )
                            DetailItem(
                                label = stringResource(
                                    id = R.string.exchange_detail_data_trade_count_label
                                ),
                                value = dataTradeCount?.toString()
                            )
                            DetailItem(
                                label = stringResource(
                                    id = R.string.exchange_detail_data_symbols_count_label
                                ),
                                value = dataSymbolsCount?.toString()
                            )
                            DetailItem(
                                label = stringResource(
                                    id = R.string.exchange_detail_volume_1hrs_usd_label
                                ),
                                value = volumeOneHrsUsd?.formatDecimalPlaces()
                            )
                            DetailItem(
                                label = stringResource(
                                    id = R.string.exchange_detail_volume_1day_usd_label
                                ),
                                value = volumeOneDayUsd.formatDecimalPlaces()
                            )
                            DetailItem(
                                label = stringResource(
                                    id = R.string.exchange_detail_volume_1mth_usd_label
                                ),
                                value = volumeOneMthUsd?.formatDecimalPlaces()
                            )
                            DetailItem(
                                label = stringResource(
                                    id = R.string.exchange_detail_metrics_label
                                ),
                                value = metricId?.joinToString(", ")
                            )

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DetailItem(label: String, value: String?) {
    if (!value.isNullOrBlank()) {
        Column(modifier = Modifier.padding(bottom = 8.dp)) {
            Text(text = label, fontWeight = FontWeight.Bold)
            Text(text = value)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExchangeDetailScreen() {
    ExchangeDetailScreen(
        exchangeId = "1",
        onBackClick = {}
    )
}


