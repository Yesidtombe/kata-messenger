package com.dojo.globant.mymessenger.feature.home.add.ui.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dojo.globant.mymessenger.R
import com.dojo.globant.mymessenger.feature.home.add.data.model.Contact
import com.dojo.globant.mymessenger.ui.theme.Body
import com.dojo.globant.mymessenger.ui.theme.Green
import com.dojo.globant.mymessenger.ui.theme.Typography

@Composable
fun ItemContact(contact: Contact, onClickContact: () -> Unit) {
    Row(
        modifier = Modifier.clickable { onClickContact() },
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
                text = contact.name,
                style = Typography.bodyLarge,
                fontSize = 17.sp
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = contact.numbers.firstOrNull().orEmpty(),
                    color = Body,
                    fontSize = 14.sp,
                    maxLines = 1
                )
            }
        }
        Icon(
            modifier = Modifier.weight(1f),
            imageVector = Icons.Default.Email,
            contentDescription = null,
            tint = Green
        )
    }
    Divider(
        modifier = Modifier.padding(top = 6.dp),
        thickness = 0.6.dp,
        color = Body
    )
}

@Preview(showBackground = true)
@Composable
fun MyPreviewContact() {
    val contact = Contact(id = "1", "Felipe Tombé")
    contact.numbers.add("3114447788")
    ItemContact(contact) { }
}