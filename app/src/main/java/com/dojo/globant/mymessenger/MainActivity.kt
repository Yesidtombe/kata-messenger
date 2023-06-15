package com.dojo.globant.mymessenger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.dojo.globant.mymessenger.common.util.hasPermission
import com.dojo.globant.mymessenger.common.util.requestPermissionWithRationale
import com.dojo.globant.mymessenger.core.navigation.graphs.RootNavigationGraph
import com.dojo.globant.mymessenger.ui.theme.MyMessengerTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val CONTACTS_READ_REQ_CODE = 100

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setContent {
            MyMessengerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootNavigationGraph(navController = rememberAnimatedNavController())
                }
            }
        }
    }

    private fun init() {
        if (!hasPermission(android.Manifest.permission.READ_CONTACTS)) {
            requestPermissionWithRationale(android.Manifest.permission.READ_CONTACTS, CONTACTS_READ_REQ_CODE, getString(R.string.contact_permission_rationale))
        }
    }
}