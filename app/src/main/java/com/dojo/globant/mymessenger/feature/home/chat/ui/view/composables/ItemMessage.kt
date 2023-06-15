package com.dojo.globant.mymessenger.feature.home.chat.ui.view.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dojo.globant.mymessenger.feature.home.chat.data.model.Message
import com.dojo.globant.mymessenger.ui.theme.*

@Composable
fun ItemMessage(
    message: Message,
    time: String
) {
    Card(
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = 16.dp,
            bottomEnd = 0.dp
        ),
        colors = CardDefaults.cardColors(containerColor = BlueGradient)
    ) {
        Text(
            modifier = Modifier
                .padding(12.dp),
            text = message.content,
            style = Typography.bodyLarge,
            color = White
        )
        Text(
            modifier = Modifier
                .padding(end = 12.dp, bottom = 12.dp)
                .align(End),
            textAlign = TextAlign.End,
            text = time,
            style = Typography.labelSmall,
            fontSize = 12.sp,
            color = Stroke
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyPreviewItemMessage() {
    Box(Modifier.background(Green)) {
        ItemMessage(Message(), "")
    }
}