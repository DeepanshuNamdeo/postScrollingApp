package com.android.deepanshunamdeo.postscrollingapp.data.api

import com.android.deepanshunamdeo.postscrollingapp.data.model.Comments
import com.android.deepanshunamdeo.postscrollingapp.data.model.User
import io.reactivex.Single

interface ApiService {

    fun getUsers(): Single<List<User>>
    fun getComments(): Single<List<Comments>>

}