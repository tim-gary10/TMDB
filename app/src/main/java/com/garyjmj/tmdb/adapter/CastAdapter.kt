package com.garyjmj.tmdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.garyjmj.tmdb.R
import com.garyjmj.tmdb.data.Constants
import com.garyjmj.tmdb.data.Movie
import com.garyjmj.tmdb.data.MovieCast
import kotlinx.android.synthetic.main.rv_item.view.*

class CastAdapter(private val movieList : List<MovieCast>, var clickListener: OnCastItemClickListener):
        RecyclerView.Adapter<CastAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: MovieCast, action: OnCastItemClickListener) {

            itemView.item_title.text = item.name

            Glide.with(itemView)
                    .load(Constants.IMAGE_BASE_W342 + item.profile_path)
                    .transform(CenterCrop())
                    .into(itemView.item_movie_poster)

            itemView.setOnClickListener{
                action.onCastItemClick(item, adapterPosition)
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

interface OnCastItemClickListener {
    fun onCastItemClick(item: MovieCast, position: Int)
}