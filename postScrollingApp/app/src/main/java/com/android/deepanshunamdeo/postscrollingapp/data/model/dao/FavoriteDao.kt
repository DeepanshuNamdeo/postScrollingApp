package com.android.deepanshunamdeo.postscrollingapp.data.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.deepanshunamdeo.postscrollingapp.data.model.FavoritePost

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoritePost(favoritePost: FavoritePost)

    @Query("SELECT * FROM favorite_table")
    fun getAllFavoritePosts() : LiveData<List<FavoritePost>>
}