package com.navi.musicplayerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.navi.musicplayerapp.R
import com.navi.musicplayerapp.data.responses.ApiResponseStatus
import com.navi.musicplayerapp.domain.model.TrackModel
import com.navi.musicplayerapp.ui.MusicViewModel
import com.navi.musicplayerapp.ui.components.LoadingComponent
import com.navi.musicplayerapp.ui.components.TitleComponent
import com.navi.musicplayerapp.ui.uidefault.theme.*

@Composable
fun HomeScreen(viewModel: MusicViewModel) {

    when (viewModel.status.value) {
        is ApiResponseStatus.Loading -> {
            LoadingComponent()
        }
        is ApiResponseStatus.Error -> {}
        is ApiResponseStatus.Success -> {
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
                                    .size(180.dp)
                                    .clip(RoundedCornerShape(dp16))
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(dp24))
                val trackList =
                    (viewModel.status.value as ApiResponseStatus.Success).data as List<TrackModel>
                LazyRow(
                    modifier = Modifier.padding(vertical = dp16),
                    horizontalArrangement = Arrangement.spacedBy(dp12),
                    content = {
                        items(trackList) {
                            Text(text = it.title)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(dp16))
                BottomList(trackList)
            }
        }
        null -> TODO()
    }

}

@Composable
fun BottomList(trackList: List<TrackModel>) {
    LazyColumn(modifier = Modifier.fillMaxWidth(),
        content = {
            items(trackList) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(it.album.cover),
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
                            text = it.title_short,
                            fontSize = sp18,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                        Text(
                            text = it.artist.name,
                            fontSize = sp14,
                            maxLines = 1,
                            color = Color.LightGray
                        )
                    }
                    Spacer(modifier = Modifier.padding(dp8))
                    Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Add to Favorite")
                }
            }
        }
    )
}