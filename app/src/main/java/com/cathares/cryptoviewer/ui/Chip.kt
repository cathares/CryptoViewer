package com.cathares.cryptoviewer.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cathares.cryptoviewer.ui.theme.Black
import com.cathares.cryptoviewer.ui.theme.BlackTransparent
import com.cathares.cryptoviewer.ui.theme.OrangeChipActive
import com.cathares.cryptoviewer.ui.theme.OrangeChipActiveText



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chip(name: String){
    var selected by remember { mutableStateOf(false) }
    FilterChip(
        onClick = { selected = !selected },
        selected = selected,
        modifier = Modifier.size(89.dp, 32.dp),
        label = { Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = name,
            )
        } },
        shape = RoundedCornerShape(16.dp),
        colors =  FilterChipDefaults.filterChipColors(
            selectedContainerColor = OrangeChipActive,
            selectedLabelColor = OrangeChipActiveText,
            containerColor = BlackTransparent,
            labelColor = Black
        )
    )
}

@Composable
fun ChipGroup() {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)){
        Chip(name = "USD  ")
        Chip(name = "RUB  ")
    }
}

@Preview(showBackground = true)
@Composable
fun ChipGroupPreview(){

    ChipGroup()
}