package com.dojo.globant.mymessenger.feature.home.list.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dojo.globant.mymessenger.feature.home.list.ui.view.composables.ItemChat
import com.dojo.globant.mymessenger.feature.home.list.ui.viewmodel.ListViewModel
import com.dojo.globant.mymessenger.ui.theme.Background

@Composable
fun ListChatScreen(
    viewModel: ListViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    onClickChat: () -> Unit
) {

    val state = viewModel.listChatState

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        color = Background
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(state){
                    ItemChat(it) {
                        onClickChat()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyListChatPreview() {
    ListChatScreen(paddingValues = PaddingValues(8.dp)) { }
}