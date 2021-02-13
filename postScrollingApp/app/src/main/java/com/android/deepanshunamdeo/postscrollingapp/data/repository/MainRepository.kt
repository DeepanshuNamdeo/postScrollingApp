package com.android.deepanshunamdeo.postscrollingapp.data.repository

import com.android.deepanshunamdeo.postscrollingapp.data.api.ApiHelper
import com.android.deepanshunamdeo.postscrollingapp.data.model.Comments
import com.android.deepanshunamdeo.postscrollingapp.data.model.Post
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {

    fun getPosts(): Single<List<Post>> {
        return apiHelper.getPost()
    }
    fun getComments(): Single<List<Comments>> {
        return apiHelper.getComments()
    }

}