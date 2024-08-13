package com.cathares.cryptoviewer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cathares.cryptoviewer.ui.screens.TokenInfoScreen
import com.cathares.cryptoviewer.ui.screens.TokenListScreen
import com.cathares.cryptoviewer.ui.viemodel.TokenInfoViewModel
import com.cathares.cryptoviewer.ui.viemodel.TokenListViewModel
import org.koin.androidx.compose.koinViewModel


sealed class Screens(val route: String) {
    data object TokenListScreen: Screens("tokens")
    data object TokenInfoScreen: Screens("info")
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
) {
    val tokenInfoViewModel: TokenInfoViewModel = koinViewModel()
    val tokenListViewModel: TokenListViewModel = koinViewModel()
    val tokenListUIState by tokenListViewModel.tokenListUIState.collectAsStateWithLifecycle()
    val tokenInfoUIState by tokenInfoViewModel.tokenInfoUIState.collectAsStateWithLifecycle()
    NavHost(
        navController = navController,
        startDestination = Screens.TokenListScreen.route
    ) {
        composable(Screens.TokenListScreen.route) {
            TokenListScreen(
                onClick = { navController.navigate(Screens.TokenInfoScreen.route) },
                tokenListViewModel = tokenListViewModel,
                tokenListUIState = tokenListUIState,
                tokenInfoViewModel = tokenInfoViewModel
            )
        }
        composable(Screens.TokenInfoScreen.route) {
            TokenInfoScreen(
                navigateBack = {navController.popBackStack()},
                tokenInfoViewModel = tokenInfoViewModel,
                tokenInfoUIState = tokenInfoUIState
            )
        }
    }
}