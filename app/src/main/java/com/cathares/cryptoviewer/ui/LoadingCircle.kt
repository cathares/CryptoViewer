package com.cathares.cryptoviewer.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cathares.cryptoviewer.ui.theme.Orange

@Composable
fun LoadingCircle() {
    var loading by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (!loading) return
        CircularProgressIndicator(
            modifier = Modifier.width(44.dp),
            color = Orange,
            trackColor = Color.Transparent
        )
    }
}