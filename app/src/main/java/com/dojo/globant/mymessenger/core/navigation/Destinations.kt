package com.dojo.globant.mymessenger.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.dojo.globant.mymessenger.R
import com.dojo.globant.mymessenger.common.util.UiText

sealed class Destinations(
    val route: String,
    val title: UiText,
    val icon: ImageVector
) {
    object ListChatScreen: Destinations(
        route = "list",
        title = UiText.StringResource(id = R.string.list_chat_item),
        icon = Icons.Default.List
    )
    object NewChatScreen: Destinations(
        route = "new",
        title = UiText.StringResource(id = R.string.new_chat_item),
        icon = Icons.Default.Add
    )
    object ProfileScreen: Destinations(
        route = "profile",
        title = UiText.StringResource(id = R.string.profile_item),
        icon = Icons.Default.Person
    )
}