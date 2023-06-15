package com.capstone.hydroandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.hydroandroid.data.network.response.profile.UserBlogItem
import com.capstone.hydroandroid.databinding.ItemBlogBinding

class ProfileBlogAdapter (private val item : List<UserBlogItem>) : RecyclerView.Adapter<ProfileBlogAdapter.MainViewHolder>() {
    class MainViewHolder(val binding: ItemBlogBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ItemBlogBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.titleTextView.text= item[position].title
        val url = item[position].photoUrl
        Glide.with(holder.itemView.context)
            .load(url)
            .into(holder.binding.photoImageView)
//        holder.itemView.setOnClickListener {
//            it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(item[position].blogId))
//        }
    }
    override fun getItemCount(): Int {
        return item.size
    }
}