package com.android.deepanshunamdeo.postscrollingapp.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.deepanshunamdeo.postscrollingapp.ui.main.viewmodel.FavoriteViewModel

class FavViewModelFactory(private val application :Application) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        //val favDao = PostAppRoomDatabase.getDatabase(application).getFavoriteDao()
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}