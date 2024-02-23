package com.example.nactemsearch.network.model

import com.google.gson.annotations.SerializedName

data class ResultItemDto(
    @SerializedName("sf")
    val sf: String,
    @SerializedName("lfs")
    val lfs: List<LongformDto>
)