package com.shashi.lokalassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.shashi.lokalassignment.model.Product

class MainActivity : AppCompatActivity() {

    var productList = ArrayList<Product>()
    var TAG = "Idk"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    private fun loadProduct() {

//        binding.progressBarAM.visibility = View.VISIBLE

        val url = "https://dummyjson.com/products"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->

                // Get url of image
                var productResponse = response.getJSONArray("products")
                productList.clear()

                for (i in 0 until productResponse.length()) {

                    var productJsonObject = productResponse.getJSONObject(i)

                    var news = Product(
                        productJsonObject.getString("id"),
                        productJsonObject.getString("title"),
                        productJsonObject.getString("description"),
                        productJsonObject.getString("price"),
                        productJsonObject.getString("brand"),
                        productJsonObject.getString("category"),
                        productJsonObject.getString("thumbnail")
                    )

                }

//                newsAdapter.updateNewsList(newsList)
//                binding.rvNewsAM.adapter?.notifyDataSetChanged()
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