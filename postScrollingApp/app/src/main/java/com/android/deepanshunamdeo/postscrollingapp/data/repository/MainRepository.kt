package com.android.deepanshunamdeo.postscrollingapp.data.repository

import com.android.deepanshunamdeo.postscrollingapp.data.api.ApiHelper
import com.android.deepanshunamdeo.postscrollingapp.data.model.Comments
import com.android.deepanshunamdeo.postscrollingapp.data.model.User
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {

    fun getUsers(): Single<List<User>> {
        return apiHelper.getUsers()
    }

    fun getComments(): Single<List<Comments>> {
        return apiHelper.getComments()
    }


}