package com.aml.wlf.algorithms.infrastructure.`in`.web.response

data class SimilarityResponse(
    val baseName: String,
    val similarName: String?,
    val similarityPercentage: Double,
    val isSamePerson: Boolean
)