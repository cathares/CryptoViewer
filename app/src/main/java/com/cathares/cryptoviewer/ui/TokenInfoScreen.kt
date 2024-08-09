package com.cathares.cryptoviewer.ui

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cathares.cryptoviewer.ui.theme.BlackTransparent
import com.example.cryptoviewer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TokenInfoScreen(name: String, navigateBack: () -> Unit = {}) {
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text(text = name) },
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
        TokenInfoScreenContent(innerPadding)
    }
}

@Composable
fun TokenInfoScreenContent(innerPadding: PaddingValues) {
    Column(modifier = Modifier.padding(innerPadding)) {
        TokenDescription(description = "Something", categories = "Something more")
    }

}


@Composable
fun TokenDescription(description: String, categories: String) {
    val state = rememberScrollState() //FIX!!!
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 10.dp, 16.dp, 0.dp)
            .verticalScroll(state),
        horizontalAlignment = Alignment.Start
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.ic_btc),
                contentDescription = "Image for a token"
            )
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
    TokenInfoScreen("Bitcoin")
}