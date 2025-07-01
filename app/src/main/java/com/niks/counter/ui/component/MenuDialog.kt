package com.niks.counter.ui.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.niks.counter.R

@Composable
fun MenuDialog(
    onDismiss: () -> Unit,
    soundEnabled: Boolean,
    vibrationEnabled: Boolean,
    showButtons: Boolean,
    onToggleSound: (Boolean) -> Unit,
    onToggleVibration: (Boolean) -> Unit,
    onToggleButtons: (Boolean) -> Unit,
    githubUrl: String
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(intrinsicSize = IntrinsicSize.Min)
                .padding(16.dp),
            shape = RoundedCornerShape(24.dp),
            tonalElevation = 8.dp,
            color = MaterialTheme.colorScheme.surface
        ) {
            Column {
                SettingsRow(stringResource(R.string.sound_toggle), soundEnabled, onToggleSound)
                SettingsRow(stringResource(R.string.vibration_toggle), vibrationEnabled, onToggleVibration)
                SettingsRow(stringResource(R.string.show_buttons_toggle), showButtons, onToggleButtons)


                val context = LocalContext.current
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
                        context.startActivity(intent)
                    },
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .height(IntrinsicSize.Min)
                        .padding(vertical = 20.dp, horizontal = 16.dp)
                ) {
                    Row {
                        Text(stringResource(R.string.link_to_github))
                        Icon(Icons.Default.Build, contentDescription = "github page")
                    }
                }


            }
        }
    }
}