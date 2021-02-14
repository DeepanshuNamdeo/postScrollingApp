package com.android.deepanshunamdeo.postscrollingapp.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.deepanshunamdeo.postscrollingapp.data.database.PostAppRoomDatabase
import com.android.deepanshunamdeo.postscrollingapp.data.model.FavoritePost
import com.android.deepanshunamdeo.postscrollingapp.data.repository.FavoritePostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(private val application: Application) : ViewModel() {


    val favoritePosts : LiveData<List<FavoritePost>>

    init {
        val favoriteDao  = PostAppRoomDatabase.getDatabase(application).getFavoriteDao()
        val repository = FavoritePostRepository(favoriteDao)
        favoritePosts = repository.getAllFavoritePost
    }

    fun addToFavorite(favoritePost: FavoritePost) = viewModelScope.launch(Dispatchers.IO) {
        FavoritePostRepository(PostAppRoomDatabase.getDatabase(application).getFavoriteDao()).insertFavorite(favoritePost)
    }

}