package com.prateek.android.retrofit_rxjava_mvvm.retrofit

import androidx.lifecycle.Observer
import com.prateek.android.retrofit_rxjava_mvvm.model.Post
import com.prateek.android.retrofit_rxjava_mvvm.model.PostNew
import com.prateek.android.retrofit_rxjava_mvvm.model.User
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.stream.Collectors

class PostRepository {
    companion object {
        val INSTANCE: PostRepository by lazy {
            PostRepository()
        }
    }

    private val postApi: PostApi = RetroHTTP.INSTANCE.getService(PostApi::class.java)

    fun getPostObservable(): Observable<List<PostNew>> {
        return postApi.getPosts()
            .map { getEvenIdPosts(it) }
            .map { getNewPosts(it) }
    }


    private fun getNewPosts(postList: List<Post>): List<PostNew> {
        val postNewList = mutableListOf<PostNew>()
        for (post: Post in postList) {
            postNewList.add(PostNew(post.title))
        }
        return postNewList
    }

    private fun getEvenIdPosts(postList: List<Post>): List<Post> {
        return postList.stream().filter { post -> isEvenId(post.id) }
            .collect(Collectors.toList())
    }



     fun getUserForPost(): Observable<User> {
        return postApi.getPost()
            .flatMap { post -> postApi.getUser() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    private fun isEvenId(id: Int?): Boolean {
        if (id != null) {
            return id % 2 == 0
        } else return false
    }
}