package com.android.deepanshunamdeo.postscrollingapp.data.repository

import androidx.lifecycle.LiveData
import com.android.deepanshunamdeo.postscrollingapp.data.model.FavoritePost
import com.android.deepanshunamdeo.postscrollingapp.data.model.dao.FavoriteDao

class FavoritePostRepository(private val favoriteDao: FavoriteDao) {

    val getAllFavoritePost : LiveData<List<FavoritePost>> = favoriteDao.getAllFavoritePosts()

    suspend fun insertFavorite(post: FavoritePost){
        favoriteDao.insertFavoritePost(post)
    }

}