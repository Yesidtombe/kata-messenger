package com.dojo.globant.mymessenger.core.navigation.graphs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.dojo.globant.mymessenger.core.navigation.Destinations
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    AnimatedNavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = Destinations.ListChatScreen.route
    ) {
        composable(route = Destinations.ListChatScreen.route) {
            Text(modifier = Modifier.padding(paddingValues).clickable { navController.navigate(Graph.CHAT) }, text = "List chats")
        }
        composable(route = Destinations.NewChatScreen.route) {
            Text(text = "New chat")
        }
        composable(route = Destinations.ProfileScreen.route) {
            Text(text = "Profile")
        }
        detailsNavGraph(navController = navController)
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.CHAT,
        startDestination = ChatScreen.Conversation.route
    ) {
        composable(route = ChatScreen.Conversation.route) {
            Text(modifier = Modifier.clickable { navController.popBackStack() }, text = "Conversation")
        }
    }
}

sealed class ChatScreen(val route: String) {
    object Conversation : ChatScreen(route = "conversation")
}