package com.cathares.cryptoviewer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cathares.cryptoviewer.ui.theme.Orange
import com.cathares.cryptoviewer.ui.theme.White
import com.example.cryptoviewer.R

@Composable
fun ErrorScreen(onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_btc),
                contentDescription = "BTC icon",
            )
            Text(
                text = "Произошла какая-то ошибка :(\n" + "Попробуем снова?",
                modifier = Modifier.padding(0.dp, 13.dp, 0.dp, 30.dp)
            )
            Button(
                onClick = onClick,
                modifier = Modifier.size(175.dp, 36.dp),
                shape = RoundedCornerShape(3.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange,
                    contentColor = White
                ),
            ) {
                Text(text = "Попробовать")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview(){
    ErrorScreen(onClick = {})
}