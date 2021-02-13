package com.android.deepanshunamdeo.postscrollingapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.deepanshunamdeo.postscrollingapp.data.api.ApiHelper
import com.android.deepanshunamdeo.postscrollingapp.data.repository.MainRepository
import com.android.deepanshunamdeo.postscrollingapp.ui.main.viewmodel.CommentViewModel
import com.android.deepanshunamdeo.postscrollingapp.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }else if (modelClass.isAssignableFrom(CommentViewModel::class.java)) {
            return CommentViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}