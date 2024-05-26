package com.example.codingchallenge.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.codingchallenge.R
import com.example.codingchallenge.model.Article
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class NewsAdapter(private val articles: List<Article>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = articles[position]
        if (item.imageUrl == null) {
            val articleImage = holder.view.findViewById<ImageView>(R.id.articleImage)
            articleImage.visibility = View.GONE
        } else {
            Picasso.get()
                .load(item.imageUrl)
                .transform(CropCircleTransformation())
                .into(holder.view.findViewById<ImageView>(R.id.articleImage))
        }
        holder.view.findViewById<TextView>(R.id.articleTitle).text = item.title
    }
}
