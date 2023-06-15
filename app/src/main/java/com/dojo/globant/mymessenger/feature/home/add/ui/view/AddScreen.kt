package com.dojo.globant.mymessenger.feature.home.add.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dojo.globant.mymessenger.feature.home.add.ui.view.composables.ItemContact
import com.dojo.globant.mymessenger.feature.home.add.ui.viewmodel.AddViewModel
import com.dojo.globant.mymessenger.ui.theme.Background

@Composable
fun AddScreen(
    viewModel: AddViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    onClickContact: (String, String) -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getContacts()
    }

    val contacts = viewModel.contactsState

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
                items(contacts){
                    ItemContact(it) {
                        viewModel.saveNewChat(it)
                        onClickContact(it.id, it.name)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyListChatPreview() {
    AddScreen(paddingValues = PaddingValues(8.dp)) { _, _ -> }
}