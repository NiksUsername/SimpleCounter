package com.niks.counter.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterTopBar(
    onResetClicked: () -> Unit,
    onMenuClicked: () -> Unit
) {
    TopAppBar (
        title = {},
        navigationIcon = {
            IconButton(onClick = onResetClicked) {
                Icon(Icons.Default.Refresh, contentDescription = "Reset")
            }
        },
        actions = {
            IconButton(onClick = onMenuClicked) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
    )
}