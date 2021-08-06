package com.garyjmj.tmdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.garyjmj.tmdb.R
import com.garyjmj.tmdb.data.Constants.IMAGE_BASE_W342
import com.garyjmj.tmdb.data.Movie
import kotlinx.android.synthetic.main.rv_item.view.*


class MovieAdapter(private val movieList : List<Movie>, var clickListener: OnUserItemClickListener):
    RecyclerView.Adapter<MovieAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Movie, action: OnUserItemClickListener) {
//
            itemView.item_title.text = item.title
//            itemView.popular_movie_release_date.text = item.release
            Glide.with(itemView)
                    .load(IMAGE_BASE_W342 + item.poster)
                    .transform(CenterCrop())
                    .into(itemView.item_movie_poster)

            itemView.setOnClickListener{
                action.onItemClick(item, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
//        holder.bindMovie(movies.get(position))
        val item = movieList[position]
        holder.bind(item, clickListener)
    }

    override fun getItemCount(): Int = movieList.size
}

interface OnUserItemClickListener {
    fun onItemClick(item: Movie, position: Int)
}