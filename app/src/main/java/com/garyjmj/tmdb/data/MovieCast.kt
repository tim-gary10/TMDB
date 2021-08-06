package com.garyjmj.tmdb.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MovieCast(
        @SerializedName("id")
        val id: Long?,

        @SerializedName("name")
        val name: String?,

        @SerializedName("original_name")
        val original_name : String?,

        @SerializedName("profile_path")
        val profile_path : String?,

        @SerializedName("popularity")
        val popularity: Float?,

        @SerializedName("character")
        val character: String?

        ): Parcelable

@Parcelize
data class MovieCastResponse(
        @SerializedName("cast")
        val casts: List<MovieCast>
): Parcelable