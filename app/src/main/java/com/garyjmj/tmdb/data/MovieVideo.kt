package com.garyjmj.tmdb.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieVideo(
        @SerializedName("id")
        val id: String?,

        @SerializedName("name")
        val name: String?,

        @SerializedName("key")
        val key : String?,

        @SerializedName("type")
        val type : String?,

): Parcelable

@Parcelize
data class MovieVideoResponse(
        @SerializedName("results")
        val videos: List<MovieVideo>
): Parcelable