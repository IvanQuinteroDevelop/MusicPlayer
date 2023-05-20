package com.navi.musicplayerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.navi.musicplayerapp.R
import com.navi.musicplayerapp.ui.components.TitleComponent
import com.navi.musicplayerapp.ui.uidefault.theme.dp16
import com.navi.musicplayerapp.ui.uidefault.theme.dp8

@Composable
fun FavoriteScreen() {
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
    Column(Modifier.fillMaxSize()) {
        TitleComponent(text = stringResource(R.string.favorite_label))
        LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
            items(categories){
                Column(Modifier.padding(dp8)) {
                    Image(
                        painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        modifier = Modifier
                            .size(170.dp)
                            .clip(RoundedCornerShape(dp16))
                    )
                    Text(text = it)
                }
            }
        })
    }
}