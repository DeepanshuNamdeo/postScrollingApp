package com.android.deepanshunamdeo.postscrollingapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.deepanshunamdeo.postscrollingapp.R
import com.android.deepanshunamdeo.postscrollingapp.data.model.Comments
import kotlinx.android.synthetic.main.comment_item_layout.view.*

class CommentAdaptor(private val comments: ArrayList<Comments>) : RecyclerView.Adapter<CommentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.comment_item_layout, parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    fun addData(list: List<Comments>) {
        comments.addAll(list)
    }
}

class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun bind(comments: Comments) {
        itemView.textViewUserName.text = comments.name
        itemView.textViewUseremail.text = comments.email
        itemView.comment_body.text = comments.body
    }
}