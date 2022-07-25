package com.example.musicproject.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class AlbumResponse(
    val results: List<RandomAlbum>
)

@Parcelize
data class RandomAlbum(
    val artistName: String,
    val collectionName: String,
    val artworkUrl60: String,
    val trackPrice: Double
): Parcelable
