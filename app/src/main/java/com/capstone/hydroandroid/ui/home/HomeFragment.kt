package com.capstone.hydroandroid.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.adapter.BlogAdapter
import com.capstone.hydroandroid.adapter.CarouselAdapter
import com.capstone.hydroandroid.adapter.CarouselData
import com.capstone.hydroandroid.adapter.SearchAdapter
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.databinding.FragmentHomeBinding
import com.capstone.hydroandroid.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding: FragmentHomeBinding by viewBinding()
    private val blogViewModel: HomeViewModel by viewModel()
    private val searchViewModel: SearchViewModel by viewModel()

    //menambahkan carousel dari recyclerview dan timer
    private lateinit var recyclerView: RecyclerView
    private lateinit var carouselAdapter: CarouselAdapter

    private lateinit var autoScrollTimer: Timer
    private val AUTO_SCROLL_DELAY: Long = 3000

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchBlog()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("cekQuery",query.toString())
                binding.searchView.clearFocus()
                if (query != null) {
                    fetchDataSearch(query)
                } else {
                    fetchBlog()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == ""){
                    Log.d("cekQueryCHANGE","asdasds")
                    fetchBlog()
                }

                return true
            }
        }
        )
        fetchUsername()

        recyclerView = view.findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        carouselAdapter = CarouselAdapter(getItems())
        recyclerView.adapter = carouselAdapter

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        startAutoScroll()
    }

    private fun startAutoScroll() {
        autoScrollTimer = Timer()
        autoScrollTimer.schedule(object : TimerTask() {
            override fun run() {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val nextPosition = (firstVisibleItemPosition + 1) % carouselAdapter.itemCount

                requireActivity().runOnUiThread {
                    recyclerView.smoothScrollToPosition(nextPosition)
                }
            }
        }, AUTO_SCROLL_DELAY, AUTO_SCROLL_DELAY)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        autoScrollTimer.cancel()
    }

    private fun getItems(): List<CarouselData> {
        val carouselDataList = mutableListOf<CarouselData>()
        carouselDataList.add(CarouselData(R.drawable.banner1))
        carouselDataList.add(CarouselData(R.drawable.banner2))
        carouselDataList.add(CarouselData(R.drawable.banner3))
        return carouselDataList
    }

    private fun fetchBlog() {

        blogViewModel.getAllBlog().observe(viewLifecycleOwner) {
            when (it) {
                is EventResult.Error -> {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                is EventResult.Loading -> {
                }
                is EventResult.Success -> {
                    //BLOG
                    val blogAdapter = BlogAdapter(it.data.blogs)
                    val layoutManager = GridLayoutManager(
                        requireContext(), 2,
                        GridLayoutManager.VERTICAL, false
                    )
                    binding.rvBlog.layoutManager = layoutManager
                    binding.rvBlog.adapter = blogAdapter
                }
            }
        }
    }

    private fun fetchDataSearch(query: String) {
        searchViewModel.getSearchedBlog(query).observe(viewLifecycleOwner) {
            when (it) {
                is EventResult.Error -> {
                }
                is EventResult.Loading -> {
                }
                is EventResult.Success -> {
                    val adapter = SearchAdapter(it.data.response)
                    val layoutManager = GridLayoutManager(
                        requireContext(), 2,
                        GridLayoutManager.VERTICAL, false
                    )
                    binding.rvBlog.setHasFixedSize(true)
                    binding.rvBlog.layoutManager = layoutManager
                    binding.rvBlog.adapter = adapter
                }
            }
        }
    }

    private fun fetchUsername() {
        val username = blogViewModel.getUsername()
        binding.usernameTextView.text = "Selamat Pagi $username"
    }
}