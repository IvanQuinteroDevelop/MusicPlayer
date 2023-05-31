package com.navi.musicplayerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.navi.musicplayerapp.R
import com.navi.musicplayerapp.domain.entity.TrackEntity
import com.navi.musicplayerapp.ui.MusicViewModel
import com.navi.musicplayerapp.ui.components.TitleComponent
import com.navi.musicplayerapp.ui.uidefault.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayingScreen(viewModel: MusicViewModel) {

    var track: TrackEntity? = viewModel.currentTrack.value
    val lifeCycleOwner = LocalLifecycleOwner.current
    viewModel.currentTrack.observe(lifeCycleOwner){ trackEntity ->
        track = trackEntity
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(dp8), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .padding(dp16)
                .fillMaxWidth(),
            shape = RoundedCornerShape(dp24),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Image(
                painter = rememberAsyncImagePainter(track?.cover ?: ""),
                contentDescription = "Image track",
                modifier = Modifier
                    .height(360.dp)
                    .fillMaxWidth()
            )
        }
        TitleComponent(text = track?.title ?: "Redemption", modifier = Modifier.padding(dp2))
        Text(text = track?.artistName ?: "Musa", fontSize = sp24)
        Row(
            Modifier
                .fillMaxWidth()
                .padding(dp24),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_previous_track),
                contentDescription = "Previous track",
                tint = Color.White,
                modifier = Modifier.size(dp48)
            )
            LargeFloatingActionButton(
                onClick = {},
                shape = CircleShape,
                containerColor = Color.White,
                modifier = Modifier.padding(vertical = dp8, horizontal = dp24)
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "Play Track",
                    modifier = Modifier.size(dp48)
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_next_track),
                contentDescription = "Next track",
                tint = Color.White,
                modifier = Modifier.size(dp48)
            )
        }
    }
}