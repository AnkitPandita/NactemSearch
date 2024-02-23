package com.example.nactemsearch.network.model

import com.google.gson.annotations.SerializedName

data class LongformDto(
    @SerializedName("lf")
    val lf: String,
    @SerializedName("freq")
    val freq: Int,
    @SerializedName("since")
    val since: Int,
    @SerializedName("vars")
    val vars: List<VariationDto>
)