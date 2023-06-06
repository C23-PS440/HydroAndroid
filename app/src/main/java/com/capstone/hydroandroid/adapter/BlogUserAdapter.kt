package com.capstone.hydroandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.hydroandroid.databinding.ItemBlogCrdBinding
import com.capstone.hydroandroid.ui.home.HomeFragmentDirections
import com.capstone.hydroandroid.data.network.response.blog.*
class BlogUserAdapter (private val item : List<BlogsItem?>?) : RecyclerView.Adapter<BlogUserAdapter.MainViewHolder>() {
    class MainViewHolder(val binding: ItemBlogCrdBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ItemBlogCrdBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.tittleTextView.text= item?.get(position)?.blogTitle
        val url = item?.get(position)?.imageUrl
        Glide.with(holder.itemView.context)
            .load(url)
            .into(holder.binding.photoImageView)
        holder.itemView.setOnClickListener {
            it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                item?.get(position)?.blogId.toString()))
        }
    }
    override fun getItemCount(): Int {
        return item?.size ?: 0
    }
}