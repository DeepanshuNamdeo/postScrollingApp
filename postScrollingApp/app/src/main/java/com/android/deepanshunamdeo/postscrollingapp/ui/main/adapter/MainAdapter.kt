package com.android.deepanshunamdeo.postscrollingapp.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.deepanshunamdeo.postscrollingapp.R
import com.android.deepanshunamdeo.postscrollingapp.data.model.Post
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(
    private val posts: ArrayList<Post>, val listener: IMainRecyclerViewAdaptor
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

     private var postId: Int = 0
     private var userId: Int = 0
     private var postTitle: String = ""
     private var postBody: String = ""
     private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        val viewHolder = DataViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_layout, parent,
                        false
                )
        )
        viewHolder.itemView.setOnClickListener{
            listener.onItemClicked(posts[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    fun addData(list: List<Post>) {
        posts.addAll(list)
    }



     class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         fun bind(post: Post) {
             itemView.textViewTitle.text = post.title
             itemView.textViewPostBody.text = post.body
         }
     }
}
interface IMainRecyclerViewAdaptor{
    fun onItemClicked(post: Post)
}