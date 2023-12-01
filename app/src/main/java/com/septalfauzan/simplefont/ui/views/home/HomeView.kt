package com.septalfauzan.simplefont.ui.views.home

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.septalfauzan.simplefont.R
import com.septalfauzan.simplefont.ui.theme.SimpleFontTheme
import com.septalfauzan.simplefont.ui.views.home.widgets.LoadLocalTtfFont
import com.septalfauzan.simplefont.utils.FontUtils
import java.io.File
import java.io.FileOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val fontNames = listOf("Roboto", "Poppins", "Montserrat", "Lobster Two")

    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )


    var fontFamily by remember {
        mutableStateOf(
            FontFamily(
                Font(
                    googleFont = GoogleFont("Poppins"),
                    fontProvider = provider,
                )
            )
        )
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            modifier = Modifier.padding(bottom = 32.dp),
            text = "Lorem ipsum dolor sit emet.",
            style = TextStyle(textAlign = TextAlign.Center, fontSize = 24.sp),
            fontFamily = fontFamily
        )
        LoadLocalTtfFont(onFilePicked = { uri ->
            FontUtils.pickLocalTtf(
                context,
                uri,
                callback = { loadedFontFamily -> fontFamily = loadedFontFamily })
        })
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeViewPreview() {
    SimpleFontTheme {
        HomeView()
    }
}