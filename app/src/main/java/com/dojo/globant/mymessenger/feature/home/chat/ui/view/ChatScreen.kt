package com.dojo.globant.mymessenger.feature.home.chat.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dojo.globant.mymessenger.R
import com.dojo.globant.mymessenger.common.composables.textfield.MyGenericTextField
import com.dojo.globant.mymessenger.feature.home.chat.ui.view.composables.ItemMessage
import com.dojo.globant.mymessenger.feature.home.chat.ui.viewmodel.ChatViewModel
import com.dojo.globant.mymessenger.ui.theme.*

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel()
) {
    val message = viewModel.messageState
    val state = viewModel.listChatState

    Column(Modifier.fillMaxSize()) {
        HeaderChat(
            Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        )
        Surface(
            Modifier
                .weight(8f)
                .padding(8.dp)
                .background(Background)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalAlignment = Alignment.End
            ) {
                items(state) {
                    ItemMessage(it.message)
                }
            }
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MyGenericTextField(
                modifier = Modifier.weight(8f),
                text = message.value,
                onValueChange = { viewModel.onMessageChanged(it) })
            Button(
                modifier = Modifier.weight(2f),
                shape = CircleShape,
                onClick = {
                    viewModel.newMessage(message.value, 315)
                }) {
                Icon(imageVector = Icons.Default.Send, contentDescription = null)
            }
        }
    }

}

@Composable
fun HeaderChat(modifier: Modifier) {
    Row(
        modifier = modifier
            .clickable { }
            .background(White),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.avatar_profile),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .weight(1f)
        )
        Column(
            modifier = Modifier
                .weight(4f)
                .padding(8.dp)
        ) {
            Text(
                text = "Jayden Lavoie",
                style = Typography.bodyLarge,
                fontSize = 17.sp
            )
            Text(
                text = "Status",
                color = Body,
                fontSize = 14.sp,
                maxLines = 1
            )
        }
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
            Icon(
                modifier = Modifier.clickable { },
                imageVector = Icons.Default.Phone,
                contentDescription = null,
                tint = Green
            )
        }
    }
    Divider(
        modifier = Modifier.padding(top = 6.dp),
        thickness = 0.4.dp,
        color = Body
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyChatPreview() {
    ChatScreen()
}