package com.dojo.globant.mymessenger.feature.sign_in.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dojo.globant.mymessenger.R
import com.dojo.globant.mymessenger.common.composables.indicator.ProgressBar
import com.dojo.globant.mymessenger.common.composables.textfield.MyGenericTextField
import com.dojo.globant.mymessenger.feature.sign_in.ui.SignInEvent
import com.dojo.globant.mymessenger.feature.sign_in.ui.SignInUiState
import com.dojo.globant.mymessenger.feature.sign_in.ui.viewmodel.SignInViewModel
import com.dojo.globant.mymessenger.ui.theme.Shapes
import com.dojo.globant.mymessenger.ui.theme.Typography

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    onNavigateHomeScreen: () -> Unit
) {
    val keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Email,
        imeAction = ImeAction.Next
    )

    val state = viewModel.state
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) {
        if (state.isLoading)
            ProgressBar()
        else
            SignIn(viewModel, state, keyboardOptions, onNavigateHomeScreen)

        LaunchedEffect(viewModel.isError) {
            if (viewModel.isError) {
                snackBarHostState.showSnackbar(context.getString(R.string.message_error_sign_in))
                viewModel.hideMessageError()
            }
        }
    }
}

@Composable
fun SignIn(
    viewModel: SignInViewModel,
    state: SignInUiState,
    keyboardOptions: KeyboardOptions,
    onNavigateHomeScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
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
                label = R.string.text_field_email,
                text = state.email,
                errorMessage = state.emailError,
                startIcon = Icons.Default.Phone,
                onValueChange = { viewModel.onEvent(SignInEvent.EmailChanged(it)) },
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
            onClick = { viewModel.onEvent(SignInEvent.OnSignIn { onNavigateHomeScreen() }) },
            shape = Shapes.small
        ) {
            Text(text = stringResource(R.string.text_sign_in))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MySignInPreview() {
    SignInScreen { }
}