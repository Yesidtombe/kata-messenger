package com.dojo.globant.mymessenger.feature.chat.home.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dojo.globant.mymessenger.core.navigation.Destinations
import com.dojo.globant.mymessenger.core.navigation.graphs.HomeNavGraph
import com.dojo.globant.mymessenger.feature.chat.home.ViewState
import com.dojo.globant.mymessenger.feature.chat.home.view.components.BottomNavigationBar
import com.dojo.globant.mymessenger.feature.chat.home.viewmodel.HomeViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@Composable
fun MainScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToLoginScreen: () -> Unit,
    onNavigateToHomeScreen: () -> Unit
) {
    when(viewModel.viewState) {
        ViewState.Loading -> {
            CircularProgressIndicator()
        }
        ViewState.LoggedIn -> {
            onNavigateToHomeScreen()
        }
        ViewState.NotLoggedIn -> {
            onNavigateToLoginScreen()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController = rememberAnimatedNavController()
) {
    val navigationItems = listOf(
        Destinations.ListChatScreen,
        Destinations.NewChatScreen,
        Destinations.ProfileScreen
    )

    val currentRoute = currentRoute(navController = navController)

    Scaffold(
        bottomBar = { BottomNavigationBar(
            navController = navController,
            items = navigationItems,
            currentRoute = currentRoute
        ) }
    ) {
        HomeNavGraph(navController = navController, it)
    }
}

@Composable
private fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}