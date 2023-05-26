package com.capstone.hydroandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.hydroandroid.data.network.response.home.Blog
import com.capstone.hydroandroid.databinding.ItemBlogBinding

class BlogAdapter (private val item : List<Blog>) : RecyclerView.Adapter<BlogAdapter.MainViewHolder>() {
    class MainViewHolder(val binding: ItemBlogBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ItemBlogBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.tittleTextView.text= item[position].title
        val url = item[position].photoUrl
        Glide.with(holder.itemView.context)
            .load(url)
            .into(holder.binding.photoImageView)
    }
    override fun getItemCount(): Int {
        return item.size
    }
}