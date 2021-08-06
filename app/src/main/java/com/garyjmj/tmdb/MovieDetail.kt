package com.garyjmj.tmdb

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.garyjmj.tmdb.adapter.CastAdapter
import com.garyjmj.tmdb.adapter.OnCastItemClickListener
import com.garyjmj.tmdb.adapter.OnVideoItemClickListener
import com.garyjmj.tmdb.adapter.VideoAdapter
import com.garyjmj.tmdb.data.*
import com.garyjmj.tmdb.services.MovieService
import com.garyjmj.tmdb.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.video_rv_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetail : AppCompatActivity(), OnVideoItemClickListener, OnCastItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val users = intent.getParcelableExtra<Movie>(MainActivity.MOVIE)

        title_detail.text = users!!.title
        release_date_detail.text = users.release
        movie_overview.text = users.overview
        movie_rating.rating = users.rating!! /2
        tv_rating.text = users.rating.toString()

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/${users.poster}")
            .transform(CenterCrop())
            .into(movie_poster)

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w1280/${users.backdropPath}")
                .transform(CenterCrop())
                .into(movie_backdrop)


        // For Video Trailer RV
        video_rv.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        )
        video_rv.setHasFixedSize(true)

        val movieVideoService = ServiceBuilder.ServiceBuilder.buildService(MovieService::class.java)
        val requestCall = movieVideoService.getMovieVideo(users.id!!)
        requestCall.enqueue(object : Callback<MovieVideoResponse> {

            override fun onResponse(call: Call<MovieVideoResponse>, response: Response<MovieVideoResponse>) {
                if (response.isSuccessful){
                    val movieBodyList = response.body()!!.videos
                    video_rv.adapter = VideoAdapter(movieBodyList, this@MovieDetail)

                }
            }

            override fun onFailure(call: Call<MovieVideoResponse>, t: Throwable) {
                Toast.makeText(this@MovieDetail,"Failure", Toast.LENGTH_LONG).show()
            }

        })


        // For Cast RV
        cast_rv.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        )
        cast_rv.setHasFixedSize(true)

        val movieCastService = ServiceBuilder.ServiceBuilder.buildService(MovieService::class.java)
        val requestCastCall = movieCastService.getMovieCast(users.id!!)
        requestCastCall.enqueue(object : Callback<MovieCastResponse> {

            override fun onResponse(call: Call<MovieCastResponse>, response: Response<MovieCastResponse>) {
                if (response.isSuccessful){
                    val castBodyList = response.body()!!.casts
                    cast_rv.adapter = CastAdapter(castBodyList, this@MovieDetail)

                }
            }

            override fun onFailure(call: Call<MovieCastResponse>, t: Throwable) {
                Toast.makeText(this@MovieDetail,"Failure", Toast.LENGTH_LONG).show()
            }

        })

    }

    private fun goToUrl(open:String){
        val uri:Uri = Uri.parse(open)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    override fun onItemClick(item: MovieVideo, position: Int) {

        video_item_tV.setOnClickListener {
            goToUrl("https://www.youtube.com/watch?v=${item.key}")
        }
    }

    override fun onCastItemClick(item: MovieCast, position: Int) {

    }
}
