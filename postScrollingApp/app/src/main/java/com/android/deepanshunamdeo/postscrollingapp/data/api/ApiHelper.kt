package com.android.deepanshunamdeo.postscrollingapp.data.api

import com.android.deepanshunamdeo.postscrollingapp.data.model.Comments

class ApiHelper(private val apiService: ApiService) {

    fun getUsers() = apiService.getUsers()
    fun getComments() = apiService.getComments(Comments().postId)

}