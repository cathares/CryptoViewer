package com.cathares.cryptoviewer.ui.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cathares.cryptoviewer.ui.state.TokenListUIState
import com.cathares.cryptoviewer.ui.elements.ChipGroup
import com.cathares.cryptoviewer.ui.elements.ErrorMessage
import com.cathares.cryptoviewer.ui.elements.LoadingCircle
import com.cathares.cryptoviewer.ui.theme.BlackTransparent
import com.cathares.cryptoviewer.ui.theme.GreenPositive
import com.cathares.cryptoviewer.ui.theme.RedNegative
import com.cathares.cryptoviewer.ui.theme.percentageStyle
import com.cathares.cryptoviewer.ui.theme.priceStyle
import com.cathares.cryptoviewer.ui.theme.tickerStyle
import com.cathares.cryptoviewer.ui.theme.titleLarge
import com.cathares.cryptoviewer.ui.theme.tokenNameStyle
import com.cathares.cryptoviewer.ui.viemodel.TokenInfoViewModel
import com.cathares.cryptoviewer.ui.viemodel.TokenListViewModel
import com.example.cryptoviewer.R
import java.text.DecimalFormat


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
                        text = stringResource(id = R.string.list),
                        style = titleLarge
                    )
                    Box(modifier = Modifier.padding(0.dp, 32.dp, 0.dp, 13.dp)) {
                        ChipGroup(tokenListUIState.chipSelected) { tokenListViewModel.onChipSwitch() }
                    }
                }
                HorizontalDivider(
                    modifier = Modifier.shadow(4.dp),
                    color = BlackTransparent
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

@OptIn(ExperimentalMaterial3Api::class)
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
        val pullRefreshState = rememberPullToRefreshState()
        if (pullRefreshState.isRefreshing) {
            LaunchedEffect(true) {
                tokenListViewModel.onRetryButtonClick()
                pullRefreshState.endRefresh()
            }
        }
        AnimatedVisibility(visible = tokenListUIState.isLoading) {
            LoadingCircle()
        }
        AnimatedVisibility(visible = tokenListUIState.tokens.isNotEmpty()) {
            Box(Modifier.nestedScroll(pullRefreshState.nestedScrollConnection)) {
                LazyColumn(Modifier.fillMaxSize()) {
                    if (!pullRefreshState.isRefreshing) {
                        items(tokenListUIState.tokens) { token ->
                            ListElement(
                                token.name,
                                token.image,
                                token.symbol,
                                token.currentPrice.toFloat(),
                                token.priceChangePercentage24h.toFloat(),
                                if (tokenListUIState.chipSelected) "$" else "₽",
                                onClick = {
                                    tokenInfoViewModel.onListElementClick(token.id)
                                    onClick()
                                    Log.e("INFOR", token.name)
                                }
                            )
                        }
                    }
                }
                if (pullRefreshState.progress>0||pullRefreshState.isRefreshing) {
                    PullToRefreshContainer(
                        modifier = Modifier.align(Alignment.TopCenter),
                        state = pullRefreshState,
                    )
                }
            }
        }
        AnimatedVisibility(visible = tokenListUIState.error != null) {
            ErrorMessage { tokenListViewModel.onRetryButtonClick() }
        }
    }
}


@Composable
fun ListElement(
    name: String,
    imageURL: String,
    ticker: String,
    price: Float,
    priceChangePercent: Float,
    currency: String,
    onClick: () -> Unit
    ) {
    val priceFloatFormat = DecimalFormat("#,##0.00")
    priceFloatFormat.decimalFormatSymbols = priceFloatFormat.decimalFormatSymbols.apply {
        groupingSeparator = ','
        decimalSeparator = '.'
    }
    val priceIntFormat = DecimalFormat("#,##0")
    priceIntFormat.decimalFormatSymbols = priceIntFormat.decimalFormatSymbols.apply {
        groupingSeparator = ','
        decimalSeparator = '.'
    }
    val formattedPrice = if (price % 1 == 0f) {
        priceIntFormat.format(price)
    }
    else {
        priceFloatFormat.format(price)
    }
    val formattedPercent = String.format("%.2f", priceChangePercent)
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .clickable { onClick() }) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                AsyncImage(model = imageURL, contentDescription = "Image for a token")
                Column(modifier = Modifier.padding(8.dp,0.dp)) {
                    Text(text = name, style = tokenNameStyle)
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(text = ticker.uppercase(), style = tickerStyle)
                }
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(text = "$currency $formattedPrice", style = priceStyle)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    textAlign = TextAlign.Right,
                    text = if (priceChangePercent > 0)  "+$formattedPercent%" else "$formattedPercent%",
                    style = percentageStyle,
                    color = if (priceChangePercent > 0)  GreenPositive else RedNegative
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
}


