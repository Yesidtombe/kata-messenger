package com.dojo.globant.mymessenger.feature.home.list.ui.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dojo.globant.mymessenger.R
import com.dojo.globant.mymessenger.feature.home.list.data.model.Chat
import com.dojo.globant.mymessenger.ui.theme.Body
import com.dojo.globant.mymessenger.ui.theme.Green
import com.dojo.globant.mymessenger.ui.theme.Typography

@Composable
fun ItemChat(
    chat: Chat,
    onClickChat: () -> Unit
) {
    Row(
        modifier = Modifier.clickable { onClickChat() },
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
                text = chat.contact.name,
                style = Typography.bodyLarge,
                fontSize = 17.sp
            )
            Row (verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "...",
                    color = Body,
                    fontSize = 14.sp,
                    maxLines = 1
                )
            }
        }
        Column (modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
            Text(
                text = "10:34 PM",
                color = Body,
                fontSize = 13.sp
            )
            Icon(
                modifier = Modifier.clickable {  },
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Green
            )
        }
    }
    Divider(
        modifier = Modifier.padding(top = 6.dp),
        thickness = 0.6.dp,
        color = Body
    )
}