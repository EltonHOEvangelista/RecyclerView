package com.example.movieapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    val moviePic: Int,
    val movieTitle: String,
    val movieDescription: String,
    val movieFullDescription: String,
    val movieRate: Float,
    val moviePrice: Float
) : Parcelable
