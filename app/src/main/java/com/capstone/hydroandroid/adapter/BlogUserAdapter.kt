package com.capstone.hydroandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.hydroandroid.databinding.ItemBlogCrdBinding
import com.capstone.hydroandroid.data.network.response.blog.*
import com.capstone.hydroandroid.databinding.ItemBlogBinding
import com.capstone.hydroandroid.ui.blog.BlogFragmentDirections
import com.capstone.hydroandroid.ui.home.HomeFragmentDirections

class BlogUserAdapter (private val item : List<BlogsItem?>?) : RecyclerView.Adapter<BlogUserAdapter.MainViewHolder>() {
    class MainViewHolder(val binding: ItemBlogBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ItemBlogBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.titleTextView.text= item?.get(position)?.blogTitle
        val url = item?.get(position)?.imageUrl
        Glide.with(holder.itemView.context)
            .load(url)
            .into(holder.binding.photoImageView)

        holder.itemView.setOnClickListener {
            it.findNavController().navigate(BlogFragmentDirections.actionNavigationBlogToDetailFragment(
                item?.get(position)?.blogId.toString()))
        }
    }
    override fun getItemCount(): Int {
        return item?.size ?: 0
    }
}