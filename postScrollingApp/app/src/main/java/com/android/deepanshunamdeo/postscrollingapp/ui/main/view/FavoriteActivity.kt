package com.android.deepanshunamdeo.postscrollingapp.ui.main.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.deepanshunamdeo.postscrollingapp.R
import com.android.deepanshunamdeo.postscrollingapp.ui.base.FavViewModelFactory
import com.android.deepanshunamdeo.postscrollingapp.ui.main.adapter.FavoriteAdaptor
import com.android.deepanshunamdeo.postscrollingapp.ui.main.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {
    private lateinit var adapter: FavoriteAdaptor
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        RecyclerViewFavorite.layoutManager = LinearLayoutManager(this)
        adapter = FavoriteAdaptor()
        RecyclerViewFavorite.addItemDecoration(
            DividerItemDecoration(
                RecyclerViewFavorite.context,
                (RecyclerViewFavorite.layoutManager as LinearLayoutManager).orientation
            )
        )
        RecyclerViewFavorite.adapter = adapter
    }

    private fun setupViewModel() {
       /* favoriteViewModel = ViewModelProvider(
            this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(FavoriteViewModel(application)::class.java)
   */
        favoriteViewModel = ViewModelProviders.of(
            this,
            FavViewModelFactory(application)
        ).get(FavoriteViewModel::class.java)
    }

    private fun setupObserver() {
        favoriteViewModel.favoritePosts.observe(this, Observer {list ->
            if (list!=null && list.size !=0){
                textViewNoFavAdded.visibility = View.GONE
                adapter.updateList(list)
            }else{
                RecyclerViewFavorite.visibility = View.GONE
                textViewNoFavAdded.visibility = View.VISIBLE
            }

        })
    }
}