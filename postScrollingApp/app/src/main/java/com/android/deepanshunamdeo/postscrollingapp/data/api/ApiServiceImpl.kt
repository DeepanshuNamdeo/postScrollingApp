package com.android.deepanshunamdeo.postscrollingapp.data.api

import com.android.deepanshunamdeo.postscrollingapp.data.model.Comments
import com.android.deepanshunamdeo.postscrollingapp.data.model.Post
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiServiceImpl : ApiService {

    override fun getPosts(): Single<List<Post>> {
        return Rx2AndroidNetworking.get("https://jsonplaceholder.typicode.com/posts")
            .build()
            .getObjectListSingle(Post::class.java)
    }

    override fun getComments(postId :Int): Single<List<Comments>> {
        return Rx2AndroidNetworking.get("https://jsonplaceholder.typicode.com/posts/$postId/comments")
                .build()
                .getObjectListSingle(Comments::class.java)
    }

}