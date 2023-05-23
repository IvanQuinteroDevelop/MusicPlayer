package com.navi.musicplayerapp.domain.model

data class TrackModel(
    val id: Long,
    val readable: Boolean,
    val title: String,
    val title_short: String,
    val title_version: String,
    val link: String,
    val duration: Int,
    val rank: Long,
    val explicit_lyrics: Boolean,
    val explicit_content_lyrics: Int,
    val explicit_content_cover: Int,
    val preview: String,
    val contributors: List<Contributor>,
    val md5_image: String,
    val artist: Artist,
    val album: Album,
    val type: String
)