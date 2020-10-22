package com.prateek.android.retrofit_rxjava_mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.prateek.android.retrofit_rxjava_mvvm.adapter.PostAdapter
import com.prateek.android.retrofit_rxjava_mvvm.model.PostNew
import com.prateek.android.retrofit_rxjava_mvvm.model.User
import com.prateek.android.retrofit_rxjava_mvvm.retrofit.PostRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            fetchPosts()
        }
        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun fetchPosts() {
        compositeDisposable.add(
            PostRepository.INSTANCE.getPostObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { postList ->
                    displayPosts(postList)
                }

        )

        compositeDisposable.add(PostRepository.INSTANCE.getUserForPost().subscribe(
            { value: User -> println("Received: ${value.street}") },      // onNext
            { error: Throwable -> println("Error: $error") },         // onError
            { println("Completed") } ))
    }

    private fun displayPosts(postList: List<PostNew>) {
        recyclerview.adapter = PostAdapter(postList)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }


}