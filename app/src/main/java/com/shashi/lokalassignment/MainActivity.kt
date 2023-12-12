package com.shashi.lokalassignment

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.shashi.lokalassignment.model.Product

class MainActivity : AppCompatActivity() {

    private var TAG = "Idk"

    private var productList = ArrayList<Product>()

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeUiComponents()
        loadProduct()

    }

    private fun initializeUiComponents() {

        productAdapter = ProductAdapter(this)
        recyclerView = findViewById(R.id.rv_products)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = productAdapter

    }

    private fun loadProduct() {

//        binding.progressBarAM.visibility = View.VISIBLE

        val url = "https://dummyjson.com/products"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->

                var productResponse = response.getJSONArray("products")
                productList.clear()

                for (i in 0 until productResponse.length()) {

                    var productJsonObject = productResponse.getJSONObject(i)

                    var product = Product(
                        productJsonObject.getString("id"),
                        productJsonObject.getString("title"),
                        productJsonObject.getString("description"),
                        productJsonObject.getString("price"),
                        productJsonObject.getString("brand"),
                        productJsonObject.getString("category"),
                        productJsonObject.getString("thumbnail")
                    )

                    productList.add(product)
                }

                productAdapter.updateProductList(productList)
                recyclerView.adapter?.notifyDataSetChanged()

//                binding.progressBarAM.visibility = View.GONE

            },
            { error ->
                showToast("Something went wrong while fetching news!")
            }
        )

        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}