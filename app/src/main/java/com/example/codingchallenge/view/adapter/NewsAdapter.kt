package com.example.codingchallenge.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.codingchallenge.R
import com.example.codingchallenge.extension.formatted
import com.example.codingchallenge.model.Article
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class NewsAdapter(private val articles: List<Article>, private val listener: NewsItemClickListener) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
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
        val articleImage = holder.view.findViewById<ImageView>(R.id.articleImage)
        val articleTitle = holder.view.findViewById<TextView>(R.id.articleTitle)
        val articleDate = holder.view.findViewById<TextView>(R.id.articleDate)

        if (item.imageUrl == null) {
            articleImage.visibility = View.GONE
        } else {
            Picasso.get()
                .load(item.imageUrl)
                .transform(CropCircleTransformation())
                .into(articleImage)
        }
        articleTitle.text = item.title
        if (item.publishedAt != null) {
            articleDate.text = item.publishedAt.formatted("HH:mm")
        }
        holder.view.setOnClickListener {
            listener.onNewsItemClicked(item)
        }
    }

    interface NewsItemClickListener {
        fun onNewsItemClicked(item: Article)
    }
}