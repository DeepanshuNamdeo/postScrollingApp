package com.android.deepanshunamdeo.postscrollingapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.deepanshunamdeo.postscrollingapp.R
import com.android.deepanshunamdeo.postscrollingapp.data.model.FavoritePost

class FavoriteAdaptor() : RecyclerView.Adapter<FavoriteAdaptor.FavoriteViewHolder>() {

    var favPost = ArrayList<FavoritePost>()

    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle = itemView.findViewById<TextView>(R.id.textViewTitle)
        val textViewPostBody = itemView.findViewById<TextView>(R.id.textViewPostBody)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
         return FavoriteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val currentFavoritePost = favPost[position]
        holder.textViewPostBody.text = currentFavoritePost.postBody
        holder.textViewTitle.text = currentFavoritePost.postTitle
    }

    override fun getItemCount(): Int {
        return favPost.count()
    }

    fun updateList(favoritePost: List<FavoritePost>){
        this.favPost.clear()
        this.favPost.addAll(favoritePost)
        notifyDataSetChanged()
    }
}

