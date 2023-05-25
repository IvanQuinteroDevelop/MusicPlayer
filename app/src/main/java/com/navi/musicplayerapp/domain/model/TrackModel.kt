package com.navi.musicplayerapp.domain.model

import com.google.gson.annotations.SerializedName

data class TrackModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("readable")
    val readable: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("title_short")
    val titleShort: String,
    @SerializedName("title_version")
    val titleVersion: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("rank")
    val rank: Long,
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean,
    @SerializedName("explicit_content_lyrics")
    val explicitContentLyrics: Int,
    @SerializedName("explicit_content_cover")
    val explicitContentCover: Int,
    @SerializedName("preview")
    val preview: String,
    @SerializedName("contributors")
    val contributors: List<Contributor>,
    @SerializedName("md5_image")
    val md5Image: String,
    @SerializedName("artist")
    val artist: Artist,
    @SerializedName("album")
    val album: Album,
    @SerializedName("type")
    val type: String
)