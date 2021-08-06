package com.garyjmj.tmdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.garyjmj.tmdb.R
import com.garyjmj.tmdb.data.MovieVideo
import kotlinx.android.synthetic.main.video_rv_item.view.*

class VideoAdapter(private val movieVideoList: List<MovieVideo>, var clickListener: OnVideoItemClickListener):
        RecyclerView.Adapter<VideoAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: MovieVideo, action: OnVideoItemClickListener) {

            itemView.video_item_tV.text = item.name
            itemView.video_type.text = item.type


            Glide.with(itemView)
                    .load("https://i.ytimg.com/vi/${item.key}/maxresdefault.jpg")
                    .transform(CenterCrop())
                    .into(itemView.video_imgV)


            itemView.setOnClickListener{
                action.onItemClick(item, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.video_rv_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val item = movieVideoList[position]
        holder.bind(item, clickListener)
    }

    override fun getItemCount(): Int = movieVideoList.size
}

interface OnVideoItemClickListener {
    fun onItemClick(item: MovieVideo, position: Int)
}