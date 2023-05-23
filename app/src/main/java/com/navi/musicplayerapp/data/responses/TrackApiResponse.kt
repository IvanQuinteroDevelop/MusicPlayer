package com.navi.musicplayerapp.data.responses

import com.google.gson.annotations.SerializedName
import com.navi.musicplayerapp.domain.model.TrackModel

data class TrackApiResponse(
    @SerializedName("data") val data: List<TrackModel>,
    @SerializedName("total") val total: Int,
    @SerializedName("next") val next: String
)