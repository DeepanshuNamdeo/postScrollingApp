package com.android.deepanshunamdeo.postscrollingapp.data.api

import com.android.deepanshunamdeo.postscrollingapp.data.model.Comments
import com.android.deepanshunamdeo.postscrollingapp.data.model.User
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiServiceImpl : ApiService{
    override fun getUsers(): Single<List<User>> {
        return Rx2AndroidNetworking.get("https://jsonplaceholder.typicode.com/posts").build().getObjectListSingle(User::class.java)
    }

    override fun getComments(postId: Int): Single<List<Comments>> {
        val url = "https://jsonplaceholder.typicode.com/posts/$postId/comments"
        return Rx2AndroidNetworking.get(url).build().getObjectListSingle(Comments::class.java)
    }
}