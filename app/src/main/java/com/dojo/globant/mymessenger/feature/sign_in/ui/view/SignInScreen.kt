package com.dojo.globant.mymessenger.feature.sign_in.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dojo.globant.mymessenger.R
import com.dojo.globant.mymessenger.common.composables.textfield.MyGenericTextField
import com.dojo.globant.mymessenger.feature.sign_in.ui.SignInEvent
import com.dojo.globant.mymessenger.feature.sign_in.ui.viewmodel.SignInViewModel
import com.dojo.globant.mymessenger.ui.theme.Shapes
import com.dojo.globant.mymessenger.ui.theme.Typography

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel()
) {
    val keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Phone,
        imeAction = ImeAction.Next
    )

    val state = viewModel.state

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
                label = R.string.text_field_phone,
                text = state.phone,
                errorMessage = state.phoneError,
                startIcon = Icons.Default.Phone,
                onValueChange = { viewModel.onEvent(SignInEvent.PhoneChanged(it)) },
                keyboardOptions = keyboardOptions
            )
            MyGenericTextField(
                label = R.string.text_field_password,
                text = state.password,
                errorMessage = state.passwordError,
                startIcon = Icons.Default.Lock,
                onValueChange = { viewModel.onEvent(SignInEvent.PasswordChanged(it)) },
                keyboardOptions = keyboardOptions.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = PasswordVisualTransformation()
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.onEvent(SignInEvent.OnSignIn) },
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