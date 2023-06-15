package com.dojo.globant.mymessenger.feature.home.profile.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import com.dojo.globant.mymessenger.ui.theme.Background
import com.dojo.globant.mymessenger.ui.theme.Body
import com.dojo.globant.mymessenger.ui.theme.Shapes
import com.dojo.globant.mymessenger.ui.theme.Typography

@Composable
fun ProfileScreen(
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(paddingValues).background(Background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
            .aspectRatio(1f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.avatar_profile),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Kevin Backer",
                        style = Typography.bodyLarge,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "3112223344",
                        color = Body,
                        fontSize = 14.sp,
                        maxLines = 1
                    )
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { /*TODO*/ },
                    shape = Shapes.medium
                ) {
                    Text(text = "Edit profile")
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MyPreviewProfile() {
    ProfileScreen(paddingValues = PaddingValues(2.dp))
}