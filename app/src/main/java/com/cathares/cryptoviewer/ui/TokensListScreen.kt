package com.cathares.cryptoviewer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cathares.cryptoviewer.ui.theme.BlackTransparent
import com.cathares.cryptoviewer.ui.theme.GreenPositive
import com.cathares.cryptoviewer.ui.theme.RedNegative
import com.cathares.cryptoviewer.ui.theme.robotoFamily
import com.example.cryptoviewer.R


@Composable
fun TokensList() {
    Scaffold(
        topBar = {
            Column {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(13.dp)
                ) {
                    Text(
                        text = "Список криптовалют", //ИСПРАВИТЬ
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        color = Color(0xFF000000).copy(alpha = 0.87f)
                    )
                    Box(modifier = Modifier.padding(0.dp, 32.dp, 0.dp, 13.dp)) {
                        ChipGroup()
                    }
                }
                Divider(
                    color = BlackTransparent,
                    modifier = Modifier.shadow(4.dp),
                )
            }
        },
    ){ innerPadding ->
        ScreenContent(innerPadding)
    }
}

@Composable
fun ScreenContent(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier.padding(innerPadding)
    ) {
        LazyColumn {
            items(20) {
                ListElement("Bitcoin", "BTC", 12434.42f, 4.05f, currency = "$"   )
            }
        }
    }
}

@Composable
fun ListElement(name: String, ticker: String, price: Float, delta: Float, currency: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ic_btc_small),
                    contentDescription = "21",
                    modifier = Modifier
                )
                Column(modifier = Modifier.padding(8.dp,0.dp)) {
                    Text(text = name)
                    Text(text = ticker)
                }
            }
            Column {
                Text(text = "$currency $price")
                Text(
                    text = if (delta > 0)  "+$delta%" else "-$delta%",
                    color = if (delta > 0)  GreenPositive else RedNegative
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    TokensList()
}


