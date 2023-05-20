package com.navi.musicplayerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.navi.musicplayerapp.R
import com.navi.musicplayerapp.ui.components.TitleComponent
import com.navi.musicplayerapp.ui.uidefault.theme.*

@Composable
fun HomeScreen() {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        TitleComponent(text = stringResource(R.string.trending_now))
        LazyRow(
            modifier = Modifier.padding(vertical = dp16),
            horizontalArrangement = Arrangement.spacedBy(dp16),
            content = {
                items(6) {
                    Image(
                        painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        modifier = Modifier
                            .size(170.dp)
                            .clip(RoundedCornerShape(dp16))
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(dp24))
        val categories = listOf(
            "Rock",
            "Hip hop",
            "Alternative",
            "Electric",
            "Reggae",
            "Popular",
            "more music",
            "Rock",
            "Hip hop",
            "Alternative",
            "Electric",
            "Reggae",
            "Popular",
            "more music"
        )
        LazyRow(
            modifier = Modifier.padding(vertical = dp16),
            horizontalArrangement = Arrangement.spacedBy(dp12),
            content = {
                items(categories) {
                    Text(text = it)
                }
            }
        )
        Spacer(modifier = Modifier.height(dp16))
        LazyColumn(modifier = Modifier.fillMaxWidth(),
            content = {
                items(categories) {
                    Row(Modifier.fillMaxWidth()) {
                        Image(
                            painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp)
                                .padding(dp8)
                                .clip(RoundedCornerShape(dp16))
                        )
                        Text(text = it, fontSize = sp16, modifier = Modifier.align(Alignment.CenterVertically))
                    }
                }
            }
        )
    }
}