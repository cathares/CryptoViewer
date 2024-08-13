package com.cathares.cryptoviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cathares.cryptoviewer.ui.theme.CryptoViewerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoViewerTheme {
                AppNavigation()
            }
        }
    }
}