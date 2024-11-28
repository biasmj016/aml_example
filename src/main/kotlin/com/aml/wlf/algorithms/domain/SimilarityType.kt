package com.aml.wlf.algorithms.domain

enum class SimilarityType(val weight: Double) {
    COSINE_SIMILARITY(15.0),
    DICE_COEFFICIENT(20.0),
    HAMMING_DISTANCE(5.0),
    JACCARD_SIMILARITY(25.0),
    JARO_WINKLER_DISTANCE(35.0)
}