package com.dojo.globant.mymessenger.core.navigation.graphs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.dojo.globant.mymessenger.core.navigation.Destinations
import com.dojo.globant.mymessenger.feature.home.add.ui.view.AddScreen
import com.dojo.globant.mymessenger.feature.home.chat.ui.view.ChatScreen
import com.dojo.globant.mymessenger.feature.home.list.ui.view.ListChatScreen
import com.dojo.globant.mymessenger.feature.home.profile.ui.view.ProfileScreen
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
            ListChatScreen(paddingValues = paddingValues) { id, name ->
                navController.navigate("${ChatScreen.Conversation.route}/$id/$name")
            }
        }
        composable(route = Destinations.NewChatScreen.route) {
            AddScreen(paddingValues = paddingValues) { id, name ->
                navController.navigate("${ChatScreen.Conversation.route}/$id/$name")
            }
        }
        composable(route = Destinations.ProfileScreen.route) {
            ProfileScreen(paddingValues = paddingValues)
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
        composable(route = "${ChatScreen.Conversation.route}/{id}/{name}") {
            ChatScreen(id = it.arguments?.getString("id").orEmpty(), name = it.arguments?.getString("name").orEmpty())
        }
    }
}

sealed class ChatScreen(val route: String) {
    object Conversation : ChatScreen(route = "conversation")
}