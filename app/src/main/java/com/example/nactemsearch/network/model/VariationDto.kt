package com.example.nactemsearch.network.model

import com.google.gson.annotations.SerializedName

data class VariationDto(
    @SerializedName("lf")
    val lf: String,
    @SerializedName("freq")
    val freq: Int,
    @SerializedName("since")
    val since: Int
)