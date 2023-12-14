package com.shashi.lokalassignment

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.shashi.lokalassignment.model.Product

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val gson = Gson()
        val product =
            gson.fromJson(intent.getStringExtra("product"), Product::class.java)

        val ivThumbnail = findViewById<ImageView>(R.id.product_thumbnail)
        val tvTitle = findViewById<TextView>(R.id.product_title)
        val tvDesc = findViewById<TextView>(R.id.product_desc)
        val tvBrand = findViewById<TextView>(R.id.product_brand)
        val tvPrice = findViewById<TextView>(R.id.product_price)
        val tvCategory = findViewById<TextView>(R.id.product_category)
        val tvRating = findViewById<TextView>(R.id.product_rating)

        Glide
            .with(this)
            .load(product.thumbnail)
            .placeholder(R.drawable.icon_placeholder)
            .into(ivThumbnail)

        tvTitle.text = product.title
        tvDesc.text = product.description
        tvBrand.text = "Brand: ${product.brand}"
        tvPrice.text = "Price: ₹${product.price}/- only"
        tvCategory.text = product.category
        tvRating.text = "Rating: ${product.rating}/5"


    }
}