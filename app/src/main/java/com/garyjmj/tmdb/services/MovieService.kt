package com.garyjmj.tmdb.services

import com.garyjmj.tmdb.data.Constants.API_KEY
import com.garyjmj.tmdb.data.Movie
import com.garyjmj.tmdb.data.MovieCastResponse
import com.garyjmj.tmdb.data.MovieResponse
import com.garyjmj.tmdb.data.MovieVideoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing?api_key=${API_KEY}")
    fun getNowPlayingMovie(): Call<MovieResponse>

    @GET("movie/popular?api_key=${API_KEY}")
    fun getPopularMovie(): Call<MovieResponse>

    @GET("movie/top_rated?api_key=${API_KEY}")
    fun getTopRatedMovie(): Call<MovieResponse>

    @GET("movie/upcoming?api_key=${API_KEY}")
    fun getUpComing(): Call<MovieResponse>

    @GET("search/movie?api_key=${API_KEY}")
    fun searchMovie(@Query("query") searchText: String): Call<MovieResponse>

    @GET("movie/{movie_id}/videos?api_key=${API_KEY}")
    fun getMovieVideo(@Path("movie_id") movie_id: Int): Call<MovieVideoResponse>

    @GET("movie/{movie_id}/credits?api_key=${API_KEY}")
    fun getMovieCast(@Path("movie_id") movie_id: Int): Call<MovieCastResponse>

}