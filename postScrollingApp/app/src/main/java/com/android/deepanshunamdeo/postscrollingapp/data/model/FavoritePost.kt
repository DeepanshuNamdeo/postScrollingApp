package com.android.deepanshunamdeo.postscrollingapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.deepanshunamdeo.postscrollingapp.utils.Constants

@Entity(tableName = Constants.FAVORITE_TABLE_NAME)
class FavoritePost (val postTitle: String,val postBody: String){
    @PrimaryKey(autoGenerate = true)var id = 0
}