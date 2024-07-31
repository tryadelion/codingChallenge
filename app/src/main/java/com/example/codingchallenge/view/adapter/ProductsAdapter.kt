package com.example.codingchallenge.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.codingchallenge.R
import com.example.codingchallenge.extension.formatted
import com.example.codingchallenge.model.Product

class ProductsAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = products[position]
        val productName = holder.view.findViewById<TextView>(R.id.productName)
        val productDate = holder.view.findViewById<TextView>(R.id.productDate)
        val productTagline = holder.view.findViewById<TextView>(R.id.productTagline)
        productTagline.text = item.tagline
        productName.text = "${item.name} (${item.rating})"
        if (item.date != null) {
            productDate.text = item.date.formatted("MMM d, yyyy")
        }
    }
}