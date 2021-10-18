package com.example.listandadaptor

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife

class PlaceDetailsActivity : AppCompatActivity() {
    @BindView(R.id.popText)
    lateinit var text: TextView

    @BindView(R.id.street)
    lateinit var streetText: TextView

    @BindView(R.id.zipCode)
    lateinit var zipCodeText: TextView

    @BindView(R.id.city)
    lateinit var cityText: TextView

    @BindView(R.id.searchButton)
    lateinit var searchButton: Button

    @BindView(R.id.shareButton)
    lateinit var shareButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_details)
        ButterKnife.bind(this)

        val street: String = intent.extras?.get("city").toString()
        val zipCode: String = intent.extras?.get("zipCode").toString()
        val city: String = intent.extras?.get("city").toString()
        streetText.text = street
        zipCodeText.text = zipCode
        cityText.text = city

        shareButton.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_SEND)
            sendIntent.putExtra(Intent.EXTRA_TEXT, "$street $city $zipCode")
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }

        searchButton.setOnClickListener {
            val url: Uri = Uri.parse("https://www.google.com/maps/search/$street $city $zipCode")
            val googleActivityIntent = Intent(Intent.ACTION_VIEW, url)
            startActivity(googleActivityIntent)
        }

        text.setOnClickListener { this.finish() }
    }
}