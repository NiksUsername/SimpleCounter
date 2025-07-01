package com.niks.counter.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CounterDisplay(
    count: String,
    showButtons: Boolean,
    onDecrement: () -> Unit,
    onIncrement: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text( text = count, style = MaterialTheme.typography.headlineLarge)

        if (showButtons) {
            Row {
                Button(onClick = onDecrement) {
                    Text("-")
                }
                Button(onClick = onIncrement) {
                    Text("+")
                }
            }
        }
    }
}