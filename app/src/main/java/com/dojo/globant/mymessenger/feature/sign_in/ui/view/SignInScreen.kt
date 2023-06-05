package com.dojo.globant.mymessenger.feature.sign_in.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dojo.globant.mymessenger.common.composables.textfield.MyGenericTextField

@Composable
fun SignInScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                modifier = Modifier.size(80.dp),
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null
            )
            Text(text = "Sign In", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text(text = "First time here? Sign Up")
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            MyGenericTextField(text = "Email", onValueChange = { })
            MyGenericTextField(text = "Password", onValueChange = { })
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {
            Text(text = "Sign In")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MySignInPreview() {
    SignInScreen()
}