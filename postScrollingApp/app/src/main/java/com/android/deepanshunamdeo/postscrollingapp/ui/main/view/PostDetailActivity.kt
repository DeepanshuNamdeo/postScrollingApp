package com.android.deepanshunamdeo.postscrollingapp.ui.main.view

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
import com.android.deepanshunamdeo.postscrollingapp.data.model.Comments
import com.android.deepanshunamdeo.postscrollingapp.data.model.FavoritePost
import com.android.deepanshunamdeo.postscrollingapp.ui.base.ViewModelFactory
import com.android.deepanshunamdeo.postscrollingapp.ui.main.adapter.CommentAdaptor
import com.android.deepanshunamdeo.postscrollingapp.ui.main.viewmodel.CommentViewModel
import com.android.deepanshunamdeo.postscrollingapp.ui.main.viewmodel.FavoriteViewModel
import com.android.deepanshunamdeo.postscrollingapp.utils.Status
import kotlinx.android.synthetic.main.activity_post_detail.*

class PostDetailActivity : AppCompatActivity() {
    private lateinit var postBody: String
    private lateinit var postTitle: String
    private var postId :Int = 0
    private lateinit var commentViewModel: CommentViewModel
    private lateinit var commentAdaptor: CommentAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        setupUI()
        setupViewModel()
        setupObserver()

    }
    private fun setupUI() {
        RecyclerViewComments.layoutManager = LinearLayoutManager(this)
        commentAdaptor = CommentAdaptor(arrayListOf())
        RecyclerViewComments.addItemDecoration(
                DividerItemDecoration(
                        RecyclerViewComments.context,
                        (RecyclerViewComments.layoutManager as LinearLayoutManager).orientation
                )
        )
        postId = intent.getIntExtra("postId",0)
         postTitle = intent.getStringExtra("postTitle").toString()
         postBody = intent.getStringExtra("postBody").toString()
        textViewPostTitle.text = postTitle
        textViewPostBody.text = postBody
        RecyclerViewComments.adapter = commentAdaptor
    }

    private fun setupViewModel() {
        commentViewModel = ViewModelProviders.of(
                this,
                ViewModelFactory(ApiHelper(ApiServiceImpl(),postId))
        ).get(CommentViewModel::class.java)
    }

    private fun setupObserver() {
        commentViewModel.getComments().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { posts -> renderList(posts) }
                    RecyclerViewComments.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    RecyclerViewComments.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(comments:  List<Comments>) {
        commentAdaptor.addData(comments)
        commentAdaptor.notifyDataSetChanged()
    }

     fun addToFavorite(view: View) {
        Toast.makeText(this, "post added to Favorites", Toast.LENGTH_SHORT).show()
        FavoriteViewModel(application).addToFavorite(favoritePost = FavoritePost(postTitle,postBody))
    }
}