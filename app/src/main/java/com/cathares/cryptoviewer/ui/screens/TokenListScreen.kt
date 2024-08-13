package com.cathares.cryptoviewer.ui.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cathares.cryptoviewer.data.TokenListUIState
import com.cathares.cryptoviewer.ui.elements.ChipGroup
import com.cathares.cryptoviewer.ui.elements.ErrorMessage
import com.cathares.cryptoviewer.ui.elements.LoadingCircle
import com.cathares.cryptoviewer.ui.theme.BlackTransparent
import com.cathares.cryptoviewer.ui.theme.GreenPositive
import com.cathares.cryptoviewer.ui.theme.RedNegative
import com.cathares.cryptoviewer.ui.theme.robotoFamily
import com.cathares.cryptoviewer.ui.viemodel.TokenInfoViewModel
import com.cathares.cryptoviewer.ui.viemodel.TokenListViewModel


@Composable
fun TokenListScreen(
    tokenListViewModel: TokenListViewModel,
    onClick: () -> Unit,
    tokenListUIState: TokenListUIState,
    tokenInfoViewModel: TokenInfoViewModel
) {
    Scaffold(
        topBar = {
            Column {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(13.dp)
                ) {
                    Text(
                        text = "Список криптовалют", //ПОЧИНИ
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        color = Color(0xFF000000).copy(alpha = 0.87f)
                    )
                    Box(modifier = Modifier.padding(0.dp, 32.dp, 0.dp, 13.dp)) {
                        ChipGroup(tokenListUIState.chipSelected) { tokenListViewModel.switchChip() }
                    }
                }
                Divider(
                    color = BlackTransparent,
                    modifier = Modifier.shadow(4.dp),
                )
            }
        },
    ){ innerPadding ->
        ScreenContent(
            innerPadding,
            tokenListUIState,
            tokenListViewModel,
            onClick,
            tokenInfoViewModel
        )
    }
}

@Composable
fun ScreenContent(
    innerPadding: PaddingValues,
    tokenListUIState: TokenListUIState,
    tokenListViewModel: TokenListViewModel,
    onClick: () -> Unit,
    tokenInfoViewModel: TokenInfoViewModel
) {
    Column(
        modifier = Modifier.padding(innerPadding)
    ) {
        AnimatedVisibility(visible = tokenListUIState.isLoading) {
            LoadingCircle()
        }
        AnimatedVisibility(visible = tokenListUIState.tokens.isNotEmpty()) {
            LazyColumn {
                items(tokenListUIState.tokens) { token ->
                    ListElement(
                        token.name,
                        token.image,
                        token.symbol,
                        token.currentPrice.toFloat(),
                        token.priceChangePercentage24h.toFloat(),
                        if (tokenListUIState.chipSelected) "$" else "₽",
                        onClick = {
                            tokenInfoViewModel.getInfo(token.id)
                            onClick()
                            Log.e("INFOR", token.name)
                        }
                    )
                }
            } 
        }
        AnimatedVisibility(visible = tokenListUIState.error != null) {
            ErrorMessage { tokenListViewModel.retry() }
        }
    }
}


@Composable
fun ListElement(
    name: String,
    imageURL: String,
    ticker: String,
    price: Float,
    delta: Float,
    currency: String,
    onClick: () -> Unit
    ) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .clickable {onClick()}) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                AsyncImage(model = imageURL, contentDescription = "Image for a token")
                Column(modifier = Modifier.padding(8.dp,0.dp)) {
                    Text(text = name)
                    Text(text = ticker.uppercase())
                }
            }
            Column {
                Text(text = "$currency $price")
                Text(
                    text = if (delta > 0)  "+$delta%" else "$delta%",
                    color = if (delta > 0)  GreenPositive else RedNegative
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
}


