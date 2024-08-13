package com.cathares.cryptoviewer.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.cathares.cryptoviewer.data.TokenInfoUIState
import com.cathares.cryptoviewer.ui.theme.BlackTransparent
import com.cathares.cryptoviewer.ui.viemodel.TokenInfoViewModel
import com.example.cryptoviewer.R
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TokenInfoScreen(
    tokenInfoViewModel: TokenInfoViewModel,
    navigateBack: () -> Unit = {},
    tokenInfoUIState: TokenInfoUIState
) {
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { tokenInfoUIState.tokenInfo?.let { Text(text = it.name) } },
                    navigationIcon = {
                        IconButton(onClick = navigateBack) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
                                contentDescription = "Arrow to navigate back"
                            )
                        }
                    },
                )
                Divider(
                    color = BlackTransparent,
                    modifier = Modifier.shadow(4.dp),
                )
            }
                 },
    ){ innerPadding ->
        TokenInfoScreenContent(innerPadding, tokenInfoViewModel, tokenInfoUIState )
    }
}

@Composable
fun TokenInfoScreenContent(
    innerPadding: PaddingValues,
    tokenInfoViewModel: TokenInfoViewModel,
    tokenInfoUIState: TokenInfoUIState
) {
    Column(modifier = Modifier.padding(innerPadding)) {
        AnimatedVisibility(visible = tokenInfoUIState.isLoading) {
            LoadingCircle()
        }
        AnimatedVisibility(visible = tokenInfoUIState.tokenInfo != null) {
            TokenDescription(
                image = tokenInfoUIState.image!!.largeImage,
                description = tokenInfoUIState.description!!.description,
                categories = tokenInfoUIState.tokenInfo!!.categories[0])
        }
        AnimatedVisibility(visible = tokenInfoUIState.error != null) {
            ErrorScreen { tokenInfoUIState.tokenInfo?.let { tokenInfoViewModel.retry(it.name) } }
        }
    }
}


@Composable
fun TokenDescription(
    image: String,
    description: String,
    categories: String
) {
    val state = rememberScrollState() //FIX!!!
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 10.dp, 16.dp, 0.dp)
            .verticalScroll(state),
        horizontalAlignment = Alignment.Start
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            AsyncImage(model = image, contentDescription = "Large image for a token")
        }
        Text(
            text = "Описание",
            modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 8.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = description,
            fontSize = 16.sp
        )
        Text(
            text = "Категории",
            modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 8.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
            )
        Text(
            text = categories,
            fontSize = 16.sp
        )
    }
}
@Preview(showBackground = true)
@Composable
fun TokenInfoPreview(){
}