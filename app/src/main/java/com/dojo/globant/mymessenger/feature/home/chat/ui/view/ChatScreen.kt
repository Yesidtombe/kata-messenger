package com.dojo.globant.mymessenger.feature.home.chat.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.dojo.globant.mymessenger.feature.home.chat.ui.viewmodel.ChatViewModel
import com.dojo.globant.mymessenger.ui.theme.Body
import com.dojo.globant.mymessenger.ui.theme.Green
import com.dojo.globant.mymessenger.ui.theme.Typography
import com.dojo.globant.mymessenger.ui.theme.White

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel()
) {

    var message by remember { mutableStateOf("") }
    //val state = viewModel.state

    Column(Modifier.fillMaxSize()) {
        HeaderChat(
            Modifier
                .weight(1f)
                .padding(horizontal = 12.dp))
        Surface(Modifier.weight(8f)) {

        }
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MyGenericTextField(modifier = Modifier.weight(8f), text = message, onValueChange = { message = it })
            Button(
                modifier = Modifier.weight(2f),
                shape = CircleShape,
                onClick = {
                //viewModel.newMessage(message)
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
        Column (modifier = Modifier
            .weight(4f)
            .padding(8.dp)) {
            Text(
                text = "Jayden Lavoie",
                style = Typography.bodyLarge,
                fontSize = 17.sp
            )
            Row (verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Status",
                    color = Body,
                    fontSize = 14.sp,
                    maxLines = 1
                )
            }
        }
        Column (modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
            Icon(
                modifier = Modifier.clickable {  },
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