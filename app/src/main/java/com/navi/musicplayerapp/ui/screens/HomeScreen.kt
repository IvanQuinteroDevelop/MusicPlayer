package com.navi.musicplayerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.navi.musicplayerapp.R
import com.navi.musicplayerapp.data.responses.ApiResponseStatus
import com.navi.musicplayerapp.domain.model.Genre
import com.navi.musicplayerapp.domain.model.TrackModel
import com.navi.musicplayerapp.ui.MusicViewModel
import com.navi.musicplayerapp.ui.components.LoadingComponent
import com.navi.musicplayerapp.ui.components.TitleComponent
import com.navi.musicplayerapp.ui.uidefault.theme.*

@Composable
fun HomeScreen(viewModel: MusicViewModel) {

    val tracksStatus by viewModel.tracksStatus.collectAsState()
    val genresStatus by viewModel.genresStatus.collectAsState()

    Column(
        Modifier.fillMaxSize()
    ) {
        TrendingRowList(tracksStatus)
        Spacer(modifier = Modifier.height(dp24))
        GenresRowList(genresStatus)
        Spacer(modifier = Modifier.height(dp16))
        //BottomList()

    }
}

@Composable
fun TrendingRowList(tracksStatus: ApiResponseStatus<Any>) {
    when (tracksStatus) {
        is ApiResponseStatus.Loading -> LoadingComponent()
        is ApiResponseStatus.Error -> {
            val errorStatus = tracksStatus as ApiResponseStatus.Error
            val errorMessage = errorStatus.messageId
            Text(text = stringResource(id = errorMessage))
        }
        is ApiResponseStatus.Success -> {
            val trackList = (tracksStatus).data as List<TrackModel>

            Column {
                TitleComponent(text = stringResource(R.string.trending_now))
                LazyRow(modifier = Modifier.padding(vertical = dp16),
                    horizontalArrangement = Arrangement.spacedBy(dp16),
                    content = {
                        items(trackList) {
                            Image(
                                painter = rememberAsyncImagePainter(it.album.cover),
                                contentScale = ContentScale.Crop,
                                contentDescription = null,
                                alpha = 0.8f,
                                modifier = Modifier
                                    .size(180.dp)
                                    .clip(RoundedCornerShape(dp16))
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun GenresRowList(genresStatus: ApiResponseStatus<Any>) {
    when (genresStatus) {
        is ApiResponseStatus.Error -> TODO()
        is ApiResponseStatus.Loading -> LoadingComponent()
        is ApiResponseStatus.Success -> {
            val genreList = (genresStatus).data as List<Genre>
            LazyRow(modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(dp12),
                content = {
                    items(genreList) {
                        Text(text = it.name)
                    }
                }
            )
        }
    }

}

@Composable
fun BottomList(trackList: List<TrackModel>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = rememberLazyListState(),
        content = {
            items(trackList) {
                ItemTrack(it)
            }
        }
    )
}

@Composable
fun ItemTrack(track: TrackModel) {
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
        ) {
            Text(
                text = track.titleShort,
                fontSize = sp18,
                fontWeight = FontWeight.Bold,
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
        Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Add to Favorite")
    }
}
