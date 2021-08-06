package com.garyjmj.tmdb.data

import android.os.Parcelable
import com.garyjmj.tmdb.MovieDetail
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
        @SerializedName("id")
        val id: Int?,

        @SerializedName("title")
        val title: String?,

        @SerializedName("poster_path")
        val poster : String?,

        @SerializedName("release_date")
        val release : String?,

        @SerializedName("overview")
        val overview: String?,

        @SerializedName("backdrop_path")
        val backdropPath: String?,

        @SerializedName("vote_average")
        val rating : Float?,


):Parcelable

@Parcelize
data class MovieResponse(
        @SerializedName("results")
        val movies: List<Movie>
        ): Parcelable
