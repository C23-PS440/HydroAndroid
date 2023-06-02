package com.capstone.hydroandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.hydroandroid.data.network.response.video.Video
import com.capstone.hydroandroid.databinding.ItemBlogBinding
import com.capstone.hydroandroid.ui.home.HomeFragmentDirections

class VideoAdapter (private val item : List<Video>) : RecyclerView.Adapter<VideoAdapter.MainViewHolder>() {
    class MainViewHolder(val binding: ItemBlogBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ItemBlogBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.tittleTextView.text= item[position].title
        val url = item[position].thumbnail
        Glide.with(holder.itemView.context)
            .load(url)
            .into(holder.binding.photoImageView)
        holder.itemView.setOnClickListener {
            it.findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToVideoFragment(item[position].videoUrl))
        }
    }
    override fun getItemCount(): Int {
        return item.size
    }
}