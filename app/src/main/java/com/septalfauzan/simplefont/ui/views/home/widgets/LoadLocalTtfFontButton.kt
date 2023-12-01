package com.septalfauzan.simplefont.ui.views.home.widgets

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun LoadLocalTtfFont(onFilePicked: (Uri?) -> Unit) {
    val pickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onFilePicked(result.data?.data)
        } else {
            onFilePicked(null)
        }
    }

    Button(onClick = {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "font/*" // For example, to pick image files
        }
        pickerLauncher.launch(intent)
    }) {
        Text("Load local font file")
    }
}
