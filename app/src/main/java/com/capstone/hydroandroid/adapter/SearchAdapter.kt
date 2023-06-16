package com.capstone.hydroandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.hydroandroid.data.network.response.search.ResponseItem
import com.capstone.hydroandroid.databinding.ItemBlogBinding
import com.capstone.hydroandroid.ui.home.HomeFragmentDirections

class SearchAdapter(
    private val listUser: List<ResponseItem?>?
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>(){
    class ViewHolder(val binding : ItemBlogBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBlogBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding){
            Glide.with(holder.itemView.context)
                .load(listUser?.get(position)?.imageUrl)
                .into(holder.binding.photoImageView)
            holder.binding.titleTextView.text = listUser?.get(position)?.blogTitle
            holder.binding.descTextView.text = listUser?.get(position)?.blogDescription
        }
        holder.itemView.setOnClickListener {
            it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                listUser?.get(position)?.blogId.toString()
            ))
        }
    }
    override fun getItemCount(): Int {
        return listUser?.size ?: 0
    }
}