package com.cathares.cryptoviewer.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cathares.cryptoviewer.ui.state.TokenInfoUIState
import com.cathares.cryptoviewer.ui.elements.ErrorMessage
import com.cathares.cryptoviewer.ui.elements.LoadingCircle
import com.cathares.cryptoviewer.ui.theme.BlackTransparent
import com.cathares.cryptoviewer.ui.theme.Typography
import com.cathares.cryptoviewer.ui.theme.bodyLarge
import com.cathares.cryptoviewer.ui.theme.bodyMedium
import com.cathares.cryptoviewer.ui.theme.titleLarge
import com.cathares.cryptoviewer.ui.viemodel.TokenInfoViewModel
import com.example.cryptoviewer.R

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
                    title = { tokenInfoUIState.tokenInfo?.let { Text(
                        text = it.name,
                        style = titleLarge,

                    ) } },
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
                categories = tokenInfoUIState.tokenInfo!!.categories)
        }
        AnimatedVisibility(visible = tokenInfoUIState.error != null) {
            ErrorMessage { tokenInfoUIState.tokenInfo?.let { tokenInfoViewModel.retry(it.name) } }
        }
    }
}


@Composable
fun TokenDescription(
    image: String,
    description: String,
    categories: List<String>,
) {
    val scrollState = rememberScrollState()
    val regex = """<a\s+href="[^"]*">(.*?)</a>""".toRegex(RegexOption.IGNORE_CASE)
    val formattedDescription = description.replace(regex) {
        it.groupValues[1]
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 10.dp, 16.dp, 0.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.Start
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            AsyncImage(model = image, contentDescription = "Large image for a token")
        }
        Text(
            text = "Описание",
            modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 8.dp),
            style = bodyLarge
        )
        Text(
            text = formattedDescription,
            style = bodyMedium
        )
        Text(
            text = "Категории",
            modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 8.dp),
            style = bodyLarge
            )
        Text(
            text = categories.joinToString(separator = ", "),
            style = bodyMedium
        )
        Spacer(modifier = Modifier.fillMaxWidth().height(34.dp))
    }
}
@Preview(showBackground = true)
@Composable
fun TokenInfoPreview(){
}