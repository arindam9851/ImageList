package com.example.imagelist.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

import com.example.imagelist.R
import com.example.imagelist.ui.main.view.MainActivity
import com.example.imageloader.ImageLoader
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ImageAdapter
constructor(private val list: List<String>, private val mContext: MainActivity) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.row_inflate_image, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        ImageLoader.with(mContext)
            .placeHolder(ContextCompat.getDrawable(mContext, R.drawable.ic_launcher_foreground)!!)
            .roundedCorners(50)
            .load(holder.imgPoster, list[position])


    }

    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPoster = itemView.findViewById(R.id.poster_image) as AppCompatImageView


    }


}