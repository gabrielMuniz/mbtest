package com.muniz.mbtest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.muniz.mbtest.ui.ExchangeDetailScreen
import com.muniz.mbtest.ui.ExchangeListScreen

sealed class Screen(val route: String) {
    object ExchangeList : Screen("exchange_list")
    object ExchangeDetail : Screen("exchange_detail/{exchangeId}") {
        fun createRoute(exchangeId: String) = "exchange_detail/$exchangeId"
    }
}

@Composable
fun MercadoBitCoinNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.ExchangeList.route
    ) {
        composable(Screen.ExchangeList.route) {
            ExchangeListScreen { exchangeId ->
                navController.navigate(Screen.ExchangeDetail.createRoute(exchangeId))
            }
        }
        composable(
            route = Screen.ExchangeDetail.route,
            arguments = listOf(navArgument("exchangeId") { type = NavType.StringType })
        ) { backStackEntry ->
            val exchangeId = backStackEntry.arguments?.getString("exchangeId") ?: ""
            ExchangeDetailScreen(
                exchangeId = exchangeId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}