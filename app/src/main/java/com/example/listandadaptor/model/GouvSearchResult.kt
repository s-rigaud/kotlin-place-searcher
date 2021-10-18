package com.example.listandadaptor.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GouvGeometry(
    @Expose
    @SerializedName("type")
    val type: String,

    @Expose
    @SerializedName("coordinates")
    val coordinates: List<Double>,
)

data class GouvProperty(
    @Expose
    @SerializedName("label")
    val label: String,

    @Expose
    @SerializedName("score")
    val score: Double,

    @Expose
    @SerializedName("id")
    val id: String,

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("postcode")
    val postcode: String,

    @Expose
    @SerializedName("citycode")
    val citycode: String,

    @Expose
    @SerializedName("x")
    val x: Double,

    @Expose
    @SerializedName("y")
    val y: Double,

    @Expose
    @SerializedName("city")
    val city: String,

    @Expose
    @SerializedName("district")
    val district: String,

    @Expose
    @SerializedName("context")
    val context: String,

    @Expose
    @SerializedName("type")
    val type: String,

    @Expose
    @SerializedName("importance")
    val importance: Double,

    )

data class GouvFeature(

    @Expose
    @SerializedName("type")
    val type: String,

    @Expose
    @SerializedName("geometry")
    val geometry: GouvGeometry,

    @Expose
    @SerializedName("properties")
    val properties: GouvProperty,
)


data class GouvSearchResult(

    @Expose
    @SerializedName("type")
    val type: String,

    @Expose
    @SerializedName("version")
    val version: String,

    @Expose
    @SerializedName("features")
    val features: List<GouvFeature>,

    @Expose
    @SerializedName("attribution")
    val attribution: String,

    @Expose
    @SerializedName("licence")
    val licence: String,

    @Expose
    @SerializedName("query")
    val query: String,

    @Expose
    @SerializedName("limit")
    val limit: Int,
)