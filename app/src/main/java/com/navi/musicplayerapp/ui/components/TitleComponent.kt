package com.navi.musicplayerapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.navi.musicplayerapp.ui.uidefault.theme.dp16
import com.navi.musicplayerapp.ui.uidefault.theme.sp32

@Composable
fun TitleComponent(text: String) {
    Text(
        text = text,
        fontSize = sp32,
        fontWeight = FontWeight.ExtraBold,
        modifier = Modifier.padding(vertical = dp16)
    )
}