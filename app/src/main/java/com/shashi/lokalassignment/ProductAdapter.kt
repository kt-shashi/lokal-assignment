package com.shashi.lokalassignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shashi.lokalassignment.model.Product

class ProductAdapter(var context: Context) :
    RecyclerView.Adapter<ProductViewHolder>() {

    private var productList = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)

        var viewHolder = ProductViewHolder(view)

        return viewHolder

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.tvTitle.text = productList.get(position).title
        holder.tvDesc.text = productList.get(position).description
        holder.tvPrice.text = "Price: ₹ ${productList.get(position).price} only"

        Glide
            .with(context)
            .load(productList.get(position).thumbnail)
            .placeholder(R.drawable.icon_placeholder)
            .into(holder.ivThumbnail)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateProductList(updatedProducts: ArrayList<Product>) {
        productList.clear()
        productList.addAll(updatedProducts)

        notifyDataSetChanged()
    }

}

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvTitle = itemView.findViewById<TextView>(R.id.item_title)
    var tvDesc = itemView.findViewById<TextView>(R.id.item_desc)
    var ivThumbnail = itemView.findViewById<ImageView>(R.id.item_thumbnail)
    var tvPrice = itemView.findViewById<TextView>(R.id.item_price)

}