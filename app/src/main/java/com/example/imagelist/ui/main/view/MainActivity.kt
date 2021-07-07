package com.example.imagelist.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imagelist.R
import com.example.imagelist.ui.main.adapter.ImageAdapter
import com.example.imagelist.ui.main.viewmodel.MainViewModel
import com.example.imagelist.utils.DataState
import com.example.imagelist.utils.PaginationScrollListener
import com.example.imagelist.utils.ItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var mAdapter: ImageAdapter
    private var mList = ArrayList<String>()
    private lateinit var layoutmanager: GridLayoutManager
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false
    private var count: Int = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUI()
        subscribeObverse()
        viewModel.setStateEvent(MainStateEvent.GetImageState(count))
    }

    private fun subscribeObverse() {
        viewModel.dataset.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<String>> -> {
                    displayProgressBar(false)
                    setData(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)

                }
                is DataState.Loading -> {
                    displayProgressBar(true)

                }
            }
        })

    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun displayError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setData(data: List<String>) {
        isLoading = false
        mList.addAll(data)
        mAdapter.notifyDataSetChanged()

    }

    private fun setUpUI() {
        mAdapter = ImageAdapter(mList, this)
        layoutmanager = GridLayoutManager(this@MainActivity, 2)

        image_recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = layoutmanager
            adapter = mAdapter
            addItemDecoration(ItemDecoration(5))

        }

        image_recyclerview?.addOnScrollListener(object :
            PaginationScrollListener(layoutmanager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }
            override fun isLoading(): Boolean {
                return isLoading
            }
            override fun loadMoreItems() {
                isLoading = true
                getMoreItems()
            }
        })

    }

    private fun getMoreItems() {
        count += 10
        if (count <= 100)
            viewModel.setStateEvent(MainStateEvent.GetImageState(count))
    }
}