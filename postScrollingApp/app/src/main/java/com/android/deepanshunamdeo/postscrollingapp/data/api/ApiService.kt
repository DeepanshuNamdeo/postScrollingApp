package com.android.deepanshunamdeo.postscrollingapp.data.api

import com.android.deepanshunamdeo.postscrollingapp.data.model.Comments
import com.android.deepanshunamdeo.postscrollingapp.data.model.Post
import io.reactivex.Single

interface ApiService {

    fun getPosts(): Single<List<Post>>
    fun getComments(postId:Int): Single<List<Comments>>

}