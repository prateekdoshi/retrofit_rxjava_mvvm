package com.prateek.android.retrofit_rxjava_mvvm.retrofit

import com.prateek.android.retrofit_rxjava_mvvm.model.Post
import com.prateek.android.retrofit_rxjava_mvvm.model.User
import io.reactivex.Observable
import retrofit2.http.GET

interface PostApi {

    @GET(URL)
    fun getPosts(): Observable<List<Post>>

    @GET(URL_ONE)
    fun getPost(): Observable<Post>

    @GET(URL_USER)
    fun getUser(): Observable<User>

    companion object {
        const val URL = "/posts"
        const val URL_ONE = "/posts/1"
        const val URL_USER = "/users/1"

    }
}