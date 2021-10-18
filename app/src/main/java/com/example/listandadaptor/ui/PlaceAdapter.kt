package com.example.listandadaptor.ui

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.listandadaptor.PlaceDetailsActivity
import com.example.listandadaptor.R
import com.example.listandadaptor.model.Place

class PlaceAdapter(private val mList: List<Place>, private val applicationContext: Context) :
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Generate UI element for each logical one
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.place_card, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Update next UI element with data
        val place = mList[position]
        holder.street.text = place.street
        holder.zipCode.text = place.zipCode
        holder.city.text = place.city

        val resource = if (position % 2 == 0) R.drawable.home else R.drawable.road
        holder.image.setImageResource(resource)
        holder.image.setOnClickListener {

            // Add song
            val mediaPlayer = MediaPlayer.create(applicationContext, R.raw.yeet)
            mediaPlayer?.start()

            val detailActivityIntent = Intent(applicationContext, PlaceDetailsActivity::class.java)
            detailActivityIntent.putExtra("street", holder.street.text)
            detailActivityIntent.putExtra("city", holder.city.text)
            detailActivityIntent.putExtra("zipCode", holder.zipCode.text)
            ContextCompat.startActivity(applicationContext, detailActivityIntent, null)
        }
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val street: TextView = itemView.findViewById(R.id.street)
        val zipCode: TextView = itemView.findViewById(R.id.zipCode)
        val city: TextView = itemView.findViewById(R.id.city)
        val image: ImageView = itemView.findViewById(R.id.image)
    }
}