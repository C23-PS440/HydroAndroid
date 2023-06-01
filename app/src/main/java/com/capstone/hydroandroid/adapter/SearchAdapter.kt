package com.capstone.hydroandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.hydroandroid.data.network.response.search.ResultItem
import com.capstone.hydroandroid.databinding.ItemBlogBinding
import com.capstone.hydroandroid.ui.search.SearchFragmentDirections

class SearchAdapter(
    private val listUser: List<ResultItem>
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>(){
    class ViewHolder(val binding : ItemBlogBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBlogBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding){
            Glide.with(holder.itemView.context)
                .load(listUser[position].photoUrl)
                .into(holder.binding.photoImageView)
            holder.binding.tittleTextView.text = listUser[position].title
        }
        holder.itemView.setOnClickListener {
            it.findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment(listUser[position].blogId))
        }
    }
    override fun getItemCount(): Int {
        return listUser.size
    }
}