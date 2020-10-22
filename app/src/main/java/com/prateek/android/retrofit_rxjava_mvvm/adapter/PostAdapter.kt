package com.prateek.android.retrofit_rxjava_mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prateek.android.retrofit_rxjava_mvvm.R
import com.prateek.android.retrofit_rxjava_mvvm.model.PostNew

class PostAdapter(private val postList: List<PostNew>) : RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val title = postList.get(position)
        holder.bind(title)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

}

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(post: PostNew) {
        val textId = itemView.findViewById(R.id.title) as TextView
        textId.text = post.title
    }
}
