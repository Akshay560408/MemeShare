package com.example.memeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity() {
    var currentImageUrl:String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun loadMeme() {

        val url = "https://meme-api.herokuapp.com/gimme"

// Request a string response from the provided URL.
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                currentImageUrl = response.getString("url")

                val memeImageView=findViewById<ImageView>(R.id.memeImageView);
                Glide.with(this).load(currentImageUrl).into(memeImageView);
            },
            {

            })

// Add the request to the RequestQueue.
       MySingleton.getInstance(this).addToRequestQueue(jsonRequest)
    }

    fun shareMeme(view: View) {
val intent=Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"Hey,check this cool meme I got from Reddit $currentImageUrl")
        val chooser=Intent.createChooser(intent,"Share this meme using..")
        startActivity(chooser)
    }

    fun nextMeme(view: View) {
loadMeme()
    }
}