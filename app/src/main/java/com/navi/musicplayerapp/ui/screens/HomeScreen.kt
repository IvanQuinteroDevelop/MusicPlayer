package com.navi.musicplayerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.navi.musicplayerapp.R
import com.navi.musicplayerapp.data.mapper.toTrackEntity
import com.navi.musicplayerapp.data.mapper.toTrackEntityList
import com.navi.musicplayerapp.data.responses.ApiResponseStatus
import com.navi.musicplayerapp.domain.entity.TrackEntity
import com.navi.musicplayerapp.domain.model.Genre
import com.navi.musicplayerapp.domain.model.TrackModel
import com.navi.musicplayerapp.ui.viewmodel.MusicViewModel
import com.navi.musicplayerapp.ui.components.LoadingComponent
import com.navi.musicplayerapp.ui.components.TitleComponent
import com.navi.musicplayerapp.ui.navigation.ScreenRoutes
import com.navi.musicplayerapp.ui.uidefault.theme.*
import com.navi.musicplayerapp.ui.viewmodel.PlayerViewModel

@Composable
fun HomeScreen(
    musicViewModel: MusicViewModel,
    playerViewModel: PlayerViewModel,
    navigationController: NavHostController
) {

    val tracksStatus by musicViewModel.tracksStatus.collectAsState()
    val genresStatus by musicViewModel.genresStatus.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = dp48)
    ) {
        TrendingRowList(tracksStatus, playerViewModel, navigationController)
        Spacer(modifier = Modifier.height(dp24))
        GenresRowList(genresStatus, musicViewModel)
        Spacer(modifier = Modifier.height(dp16))
        BottomList(musicViewModel, playerViewModel, navigationController)
    }
}

@Composable
fun TrendingRowList(
    tracksStatus: ApiResponseStatus<Any>,
    playerViewModel: PlayerViewModel,
    navigationController: NavHostController
) {
    when (tracksStatus) {
        is ApiResponseStatus.Loading -> LoadingComponent()
        is ApiResponseStatus.Error -> ErrorScreen(tracksStatus)
        is ApiResponseStatus.Success -> {
            val trackList = (tracksStatus).data as List<TrackModel>? ?: emptyList()
            playerViewModel.setTracks(trackList.toTrackEntityList())

            Column {
                TitleComponent(
                    text = stringResource(R.string.trending_now),
                    Modifier.padding(vertical = dp16)
                )
                LazyRow(modifier = Modifier.padding(vertical = dp16),
                    horizontalArrangement = Arrangement.spacedBy(dp16),
                    content = {
                        items(trackList) { track ->
                            Image(
                                painter = rememberAsyncImagePainter(track.album.cover),
                                contentScale = ContentScale.Crop,
                                contentDescription = null,
                                alpha = 0.9f,
                                modifier = Modifier
                                    .size(height = 170.dp, width = 185.dp)
                                    .clip(RoundedCornerShape(dp24))
                                    .clickable {
                                        playerViewModel.playTrack(track.toTrackEntity())
                                        navigationController.navigate(ScreenRoutes.Playing.route)
                                    }
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun GenresRowList(genresStatus: ApiResponseStatus<Any>, viewModel: MusicViewModel) {
    when (genresStatus) {
        is ApiResponseStatus.Error -> {
            ErrorScreen(genresStatus)
        }
        is ApiResponseStatus.Loading -> LoadingComponent()
        is ApiResponseStatus.Success -> {

            val genreList = (genresStatus).data as List<Genre>? ?: emptyList()
            var selectedTabIndex by rememberSaveable { mutableStateOf(0) }
            viewModel.getTracksByGenre("0")

            if (genreList.isNotEmpty()) {
                Column(Modifier.fillMaxWidth()) {
                    ScrollableTabRow(
                        selectedTabIndex = selectedTabIndex,
                        containerColor = Color.Transparent,
                        edgePadding = 0.dp,
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                                color = Color.Blue,
                                height = 2.dp
                            )
                        }
                    ) {
                        genreList.forEachIndexed { index, genre ->
                            Tab(
                                selected = selectedTabIndex == index,
                                onClick = {
                                    if (index != selectedTabIndex) {
                                        viewModel.getTracksByGenre(genre.id)
                                    }
                                    selectedTabIndex = index
                                },
                                text = { Text(text = genre.name) },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomList(
    musicViewModel: MusicViewModel,
    playerViewModel: PlayerViewModel,
    navigationController: NavHostController
) {
    val lifeCycleOwner = LocalLifecycleOwner.current
    val tracksStatus by musicViewModel.tracksByGenreStatus.collectAsState()
    var favoriteTracks: MutableList<TrackEntity> = mutableListOf()
    musicViewModel.favoriteTracks.observe(lifeCycleOwner) {
        favoriteTracks = it as MutableList<TrackEntity>
    }
    when (tracksStatus) {
        is ApiResponseStatus.Loading -> LoadingComponent()
        is ApiResponseStatus.Error -> ErrorScreen(tracksStatus as ApiResponseStatus.Error)
        is ApiResponseStatus.Success -> {
            val trackList =
                ((tracksStatus as ApiResponseStatus.Success<Any>).data as List<TrackModel>?
                    ?: emptyList())
            playerViewModel.setTracks(trackList.toTrackEntityList())
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(bottom = dp24),
                state = rememberLazyListState(),
                content = {
                    items(trackList) { track ->
                        ItemTrack(track, musicViewModel, playerViewModel, favoriteTracks, navigationController)
                    }
                }
            )
        }
    }

}

@Composable
fun ItemTrack(
    track: TrackModel,
    viewModel: MusicViewModel,
    playerViewModel: PlayerViewModel,
    favoriteTracks: MutableList<TrackEntity>,
    navigationController: NavHostController
) {
    var isFavorite by rememberSaveable {
        mutableStateOf(favoriteTracks.any { favoriteTrack -> track.id == favoriteTrack.id })
    }
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = rememberAsyncImagePainter(track.album.cover),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
                .padding(dp8)
                .clip(RoundedCornerShape(dp8))
        )
        Column(
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .padding(dp4)
                .clickable {
                    playerViewModel.playTrack(track.toTrackEntity())
                    navigationController.navigate(ScreenRoutes.Playing.route)
                }
        ) {
            Text(
                text = track.titleShort,
                fontSize = sp18,
                fontWeight = FontWeight.ExtraBold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = track.artist.name,
                fontSize = sp14,
                maxLines = 1,
                color = Color.LightGray
            )
        }
        Spacer(modifier = Modifier.padding(dp8))
        Icon(
            if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = "Favorite button",
            tint = Color.White,
            modifier = Modifier.clickable {
                if (isFavorite) {
                    viewModel.removeFavoriteTrack(track)
                } else {
                    viewModel.addFavoriteTrack(track)
                }
                isFavorite = !isFavorite
            }
        )
    }
}

@Composable
fun ErrorScreen(status: ApiResponseStatus.Error<Any>) {
    Box(Modifier.fillMaxSize()) {
        Text(text = stringResource(id = (status).messageId), fontSize = sp18)
    }
}
