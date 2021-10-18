package com.example.listandadaptor

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.listandadaptor.api_calls.ApiClient
import com.example.listandadaptor.model.GouvSearchResult
import com.example.listandadaptor.model.Place
import com.example.listandadaptor.ui.PlaceAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    val places = ArrayList<Place>()

    @BindView(R.id.textInput)
    lateinit var textInput: EditText

    @BindView(R.id.recyclerview)
    lateinit var recyclerview: RecyclerView

    @BindView(R.id.responseText)
    lateinit var responseText: TextView


    // Main Activity only instantiate and setup the recycler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        // Set custom adapter as recycler adapter
        recyclerview.adapter = PlaceAdapter(places, this)
        recyclerview.layoutManager = LinearLayoutManager(this)

        textInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                updatePlaceData(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun updatePlaceData(address: String) {
        val call: Call<GouvSearchResult> = ApiClient.getClient.getPlaces(address)
        val classContext = this
        Toast.makeText(classContext, "updated", Toast.LENGTH_SHORT).show()
        call.enqueue(object : Callback<GouvSearchResult> {

            override fun onResponse(
                call: Call<GouvSearchResult>?,
                response: Response<GouvSearchResult>?,
            ) {
                places.clear()
                places.addAll(gouvResultToPlaces(response!!.body()!!))
                recyclerview.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<GouvSearchResult>?, t: Throwable?) {
                Toast.makeText(classContext, "error !!", Toast.LENGTH_LONG).show()
                responseText.text = "ERROR !"
            }

        })
    }

    private fun gouvResultToPlaces(gouvResult: GouvSearchResult): ArrayList<Place> {
        val resultPlaces = ArrayList<Place>()
        for (feature in gouvResult.features) {
            resultPlaces.add(Place(0.0,
                0.0,
                feature.properties.name,
                feature.properties.postcode,
                feature.properties.city))
        }
        return resultPlaces
    }
}