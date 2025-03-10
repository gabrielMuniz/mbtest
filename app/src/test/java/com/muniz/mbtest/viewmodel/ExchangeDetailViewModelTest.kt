package com.muniz.mbtest.viewmodel

import com.muniz.mbtest.domain.model.ExchangeDetail
import com.muniz.mbtest.domain.repositories.ExchangeRepository
import com.muniz.mbtest.ui.ExchangeDetailViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.rules.Timeout

@OptIn(ExperimentalCoroutinesApi::class)
class ExchangeDetailViewModelTest {

    private val repository: ExchangeRepository = mockk(relaxed = true)

    private lateinit var viewModel: ExchangeDetailViewModel

    @get:Rule
    val rule: TestRule = Timeout.seconds(5)

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = ExchangeDetailViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when loadExchange is called, it should start with isLoading true`() = runTest {
        coEvery { repository.getExchangeById(any()) } returns flow { delay(1000) }

        viewModel.loadExchange("test_id")

        assertEquals(viewModel.state.value.isLoading, true)
    }

    @Test
    fun `when loadExchange retrieves data successfully, it should update the state correctly`() =
        runTest {
            val fakeExchange = ExchangeDetail(
                name = "Christian George",
                volumeOneDayUsd = 6.7,
                exchangeId = "ignota",
                website = "morbi",
                dataStart = "veritus",
                dataEnd = "an",
                dataQuoteStart = "nisl",
                dataOrderBookStart = "conubia",
                dataTradeStart = "vitae",
                dataTradeEnd = "delicata",
                dataTradeCount = 6010,
                dataSymbolsCount = 4344,
                volumeOneHrsUsd = 8.9,
                volumeOneMthUsd = 10.11,
                metricId = listOf()
            )
            coEvery { repository.getExchangeById("test_id") } returns flowOf(fakeExchange)

            viewModel.loadExchange("test_id")

            assertEquals(viewModel.state.value.isLoading, false)
            assertEquals(viewModel.state.value.exchange, fakeExchange)

        }

    @Test
    fun `when loadExchange fails, it should update the state with an error`() = runTest {
        val exception = RuntimeException("Network error")
        coEvery { repository.getExchangeById("test_id") } throws exception

        viewModel.loadExchange("test_id")

        assertEquals(viewModel.state.value.isLoading, false)
        assertEquals(viewModel.state.value.error, "Network error")

    }
}
