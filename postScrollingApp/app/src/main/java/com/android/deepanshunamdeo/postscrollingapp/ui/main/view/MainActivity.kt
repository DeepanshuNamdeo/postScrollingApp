package com.android.deepanshunamdeo.postscrollingapp.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.deepanshunamdeo.postscrollingapp.R
import com.android.deepanshunamdeo.postscrollingapp.data.api.ApiHelper
import com.android.deepanshunamdeo.postscrollingapp.data.api.ApiServiceImpl
import com.android.deepanshunamdeo.postscrollingapp.data.model.Post
import com.android.deepanshunamdeo.postscrollingapp.ui.base.ViewModelFactory
import com.android.deepanshunamdeo.postscrollingapp.ui.main.adapter.IMainRecyclerViewAdaptor
import com.android.deepanshunamdeo.postscrollingapp.ui.main.adapter.MainAdapter
import com.android.deepanshunamdeo.postscrollingapp.ui.main.viewmodel.MainViewModel
import com.mindorks.framework.mvvm.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , IMainRecyclerViewAdaptor {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf(),this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.getPosts().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { posts -> renderList(posts) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(posts: List<Post>) {
        adapter.addData(posts)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl(),postId = 0))
        ).get(MainViewModel::class.java)
    }

    override fun onItemClicked(post: Post) {
        val intent = Intent(this, PostDetailActivity::class.java)
        intent.putExtra("postId",post.id)
        intent.putExtra("postTitle",post.title)
        intent.putExtra("postBody",post.body)
        startActivity(intent)
    }

}
