package com.android.deepanshunamdeo.postscrollingapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.deepanshunamdeo.postscrollingapp.data.model.FavoritePost
import com.android.deepanshunamdeo.postscrollingapp.data.model.dao.FavoriteDao

@Database(entities = arrayOf(FavoritePost::class), version = 1, exportSchema = false)
abstract class PostAppRoomDatabase :RoomDatabase() {
    abstract fun getFavoriteDao() : FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: PostAppRoomDatabase? = null
        fun getDatabase(context: Context): PostAppRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PostAppRoomDatabase::class.java,
                    "Post_app_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}