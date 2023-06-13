package com.dojo.globant.mymessenger.feature.chat.home.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dojo.globant.mymessenger.R
import com.dojo.globant.mymessenger.core.navigation.graphs.Graph
import com.dojo.globant.mymessenger.feature.chat.home.ViewState
import com.dojo.globant.mymessenger.feature.chat.home.viewmodel.HomeViewModel
import com.dojo.globant.mymessenger.ui.theme.SkyBlue
import com.dojo.globant.mymessenger.ui.theme.White
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToLoginScreen: () -> Unit,
    onNavigateToHomeScreen: () -> Unit
) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2000)
        viewModel.isUserLogged(
            onNavigateToLoginScreen = onNavigateToLoginScreen,
            onNavigateToHomeScreen = onNavigateToHomeScreen
        )
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
    Box(
        modifier = Modifier
            .background(SkyBlue)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha),
            imageVector = Icons.Default.Email,
            contentDescription = stringResource(R.string.cd_logo_icon),
            tint = White
        )
    }
}
