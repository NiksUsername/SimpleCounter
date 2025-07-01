package com.niks.counter.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.niks.counter.ui.component.CounterDisplay
import com.niks.counter.ui.component.CounterTopBar
import com.niks.counter.ui.component.MenuDialog
import com.niks.counter.ui.component.ResetDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterScreen(
    model: CounterViewModel
) {
    var showResetDialog by remember { mutableStateOf(false) }
    var showMenu by remember { mutableStateOf(false) }


    val URL = "https://github.com/NiksUsername/SimpleCounter"



    Scaffold(
        topBar = {
            CounterTopBar(
                onMenuClicked = {showMenu = true},
                onResetClicked = {showResetDialog = true}
            )
        }

    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues), contentAlignment = Alignment.Center) {
            CounterDisplay(
                count = model.count.value.toString(),
                showButtons = model.showBtnsToggle.value,
                onDecrement = {model.decrement()},
                onIncrement = {model.increment()}
            )

        }

            if (showResetDialog) {
                ResetDialog(
                    onConfirm = {
                        model.reset()
                        showResetDialog = false
                    },
                    onDismiss = { showResetDialog = false }
                )
            }

            if (showMenu) {
                MenuDialog(
                    soundEnabled = model.soundToggle.value,
                    vibrationEnabled = model.vibrationToggle.value,
                    showButtons = model.showBtnsToggle.value,
                    onToggleVibration = {model.changeVibration()},
                    onToggleButtons = {model.changeShowButtons()},
                    onToggleSound = {model.changeSound()},
                    onDismiss = { showMenu = false},
                    githubUrl = URL
                )
            }

        }
    }