package com.garyjmj.tmdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.garyjmj.tmdb.adapter.*
import com.garyjmj.tmdb.data.Movie
import com.garyjmj.tmdb.data.MovieResponse
import com.garyjmj.tmdb.services.MovieService
import com.garyjmj.tmdb.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() , OnUserItemClickListener {

    companion object{
        val MOVIE = "USERS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        allRecyclerView()


    }

    private fun allRecyclerView(){
        // latest
        nowPlaying_rv.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        )
        nowPlaying_rv.setHasFixedSize(true)
        getNowPlayingMovieRV()

        // Top Rated
        top_rated_rv.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        )
        top_rated_rv.setHasFixedSize(true)
        getTopRateMovieRV()

        // UpComing
        upComing_rv.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        )
        upComing_rv.setHasFixedSize(true)
        getUpComingRV()

        // Popular
        popular_rv.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        )
        popular_rv.setHasFixedSize(true)
        getPopularMovieRV()

        // Search Movie
        search_rv.layoutManager = GridLayoutManager(applicationContext, 3
        )
        search_rv.setHasFixedSize(true)
        searchUser(searchEditText.text.toString())

        search_button.setOnClickListener {
            if(!TextUtils.isEmpty(searchEditText.text.toString())) {
                searchUser(searchEditText.text.toString())
                ly1.visibility = View.INVISIBLE
                ly2.visibility = View.INVISIBLE
                ly3.visibility = View.INVISIBLE
                ly4.visibility = View.INVISIBLE
                search_rv.visibility = View.VISIBLE
            } else {
                Toast.makeText(this@MainActivity,"Cannot Search", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun searchUser(searchText: String) {

        val movieService = ServiceBuilder.ServiceBuilder.buildService(MovieService::class.java)
        val requestCall = movieService.searchMovie(searchText)
        requestCall.enqueue(object : Callback<MovieResponse> {

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful){
                    val movieBodyList = response.body()!!.movies
                    search_rv.adapter = SearchAdapter(movieBodyList, this@MainActivity)
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Failure", Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun getNowPlayingMovieRV(){

        val movieService = ServiceBuilder.ServiceBuilder.buildService(MovieService::class.java)
        val requestCall = movieService.getNowPlayingMovie()
        requestCall.enqueue(object : Callback<MovieResponse> {

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful){
                    val movieBodyList = response.body()!!.movies
                    nowPlaying_rv.adapter = MovieAdapter(movieBodyList, this@MainActivity)

                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Failure", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun getTopRateMovieRV(){

        val movieService = ServiceBuilder.ServiceBuilder.buildService(MovieService::class.java)

        val requestCall = movieService.getTopRatedMovie()

        requestCall.enqueue(object : Callback<MovieResponse> {

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful){
//                    return callback(response.body()!!.movies)
                    val movieBodyList = response.body()!!.movies
                    top_rated_rv.adapter = MovieAdapter(movieBodyList, this@MainActivity)

                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Failure", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun getUpComingRV(){

        val movieService = ServiceBuilder.ServiceBuilder.buildService(MovieService::class.java)

        val requestCall = movieService.getUpComing()

        requestCall.enqueue(object : Callback<MovieResponse> {

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful){
                    val movieBodyList = response.body()!!.movies
                    upComing_rv.adapter = MovieAdapter(movieBodyList, this@MainActivity)

                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Failure", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun getPopularMovieRV(){

        val movieService = ServiceBuilder.ServiceBuilder.buildService(MovieService::class.java)
        val requestCall = movieService.getPopularMovie()
        requestCall.enqueue(object : Callback<MovieResponse> {

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful){
                    val movieBodyList = response.body()!!.movies
                    popular_rv.adapter = MovieAdapter(movieBodyList, this@MainActivity)

                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Failure", Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onItemClick(item: Movie, position: Int) {
        val movies = Movie(
                item.id,
                item.title,
                item.poster,
                item.release,
                item.overview,
                item.backdropPath,
                item.rating,)

        val intent = Intent(this, MovieDetail::class.java)
        intent.putExtra(MOVIE, movies)
        startActivity(intent)
    }
}