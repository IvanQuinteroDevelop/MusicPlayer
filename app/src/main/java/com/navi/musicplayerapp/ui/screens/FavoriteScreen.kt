package com.navi.musicplayerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.navi.musicplayerapp.R
import com.navi.musicplayerapp.domain.entity.TrackEntity
import com.navi.musicplayerapp.ui.viewmodel.MusicViewModel
import com.navi.musicplayerapp.ui.components.TitleComponent
import com.navi.musicplayerapp.ui.navigation.ScreenRoutes
import com.navi.musicplayerapp.ui.uidefault.theme.*
import com.navi.musicplayerapp.ui.viewmodel.PlayerViewModel

@Composable
fun FavoriteScreen(
    musicViewModel: MusicViewModel,
    playerViewModel: PlayerViewModel,
    navigationController: NavHostController
) {
    val lifeCycleOwner = LocalLifecycleOwner.current
    var favoriteTracks: MutableList<TrackEntity> = mutableListOf()
    musicViewModel.favoriteTracks.observe(lifeCycleOwner) {
        favoriteTracks = it as MutableList<TrackEntity>
        playerViewModel.setTracks(favoriteTracks)
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = dp36)) {
        TitleComponent(
            text = stringResource(R.string.favorite_label),
            Modifier.padding(vertical = dp16)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(bottom = dp24),
            content = {
                items(favoriteTracks) {
                    Column(Modifier.padding(dp8)) {
                        Image(
                            painter = rememberAsyncImagePainter(it.cover),
                            contentDescription = null,
                            modifier = Modifier
                                .size(170.dp)
                                .padding(dp4)
                                .clip(RoundedCornerShape(dp24))
                                .clickable {
                                    playerViewModel.playTrack(it)
                                    navigationController.navigate(ScreenRoutes.Playing.route)
                                }
                        )
                        Text(text = it.title, fontSize = sp18, fontWeight = FontWeight.Bold)
                        Text(
                            text = it.artistName,
                            fontSize = sp14,
                            color = Color.LightGray,
                            modifier = Modifier.padding(dp2)
                        )
                    }
                }
            }
        )
    }
}