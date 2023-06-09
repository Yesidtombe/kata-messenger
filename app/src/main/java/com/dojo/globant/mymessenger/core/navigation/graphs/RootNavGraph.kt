package com.dojo.globant.mymessenger.core.navigation.graphs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dojo.globant.mymessenger.feature.chat.home.view.HomeScreen
import com.dojo.globant.mymessenger.feature.chat.home.view.MainScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootNavigationGraph(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.MAIN,
    ) {
        authNavGraph(navController = navController)
        composable(route = Graph.MAIN) {
            MainScreen(
                onNavigateToLoginScreen = {
                    navController.popBackStack()
                    navController.navigate(route = Graph.AUTHENTICATION)
                },
                onNavigateToHomeScreen = {
                    navController.popBackStack()
                    navController.navigate(route = Graph.HOME)
                }
            )
        }
        composable(route = Graph.HOME) {
            HomeScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val MAIN = "main_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val CHAT = "chat_graph"
}