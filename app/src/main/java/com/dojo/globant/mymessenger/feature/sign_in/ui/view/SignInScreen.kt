package com.dojo.globant.mymessenger.feature.sign_in.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dojo.globant.mymessenger.R
import com.dojo.globant.mymessenger.common.composables.textfield.MyGenericTextField
import com.dojo.globant.mymessenger.ui.theme.Shapes
import com.dojo.globant.mymessenger.ui.theme.Typography

@Composable
fun SignInScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                modifier = Modifier.size(80.dp),
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null
            )
            Text(text = stringResource(R.string.text_sign_in), style = Typography.titleLarge)
            Text(text = stringResource(R.string.text_for_signing_up), style = Typography.bodyLarge)
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            MyGenericTextField(
                text = stringResource(R.string.text_field_phone),
                startIcon = Icons.Default.Phone,
                onValueChange = { })
            MyGenericTextField(
                text = stringResource(R.string.text_field_password),
                startIcon = Icons.Default.Lock,
                onValueChange = { })
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ },
            shape = Shapes.small
        ) {
            Text(text = stringResource(R.string.text_sign_in))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MySignInPreview() {
    SignInScreen()
}