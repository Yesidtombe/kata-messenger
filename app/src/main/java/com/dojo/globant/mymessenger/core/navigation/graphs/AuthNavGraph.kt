package com.dojo.globant.mymessenger.core.navigation.graphs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.dojo.globant.mymessenger.feature.sign_in.ui.view.SignInScreen
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            SignInScreen {
                navController.popBackStack()
                navController.navigate(route = Graph.HOME)
            }
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login: AuthScreen(route = "LOGIN")
}