package com.android.deepanshunamdeo.postscrollingapp.data.api


class ApiHelper(private val apiService: ApiService, private val postId:Int) {

    fun getPost() = apiService.getPosts()
    fun getComments() = apiService.getComments(postId)

}