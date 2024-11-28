package com.aml.wlf.algorithms.domain

enum class SimilarityType(val weight: Double) {
    COSINE_SIMILARITY(30.0),
    DICE_COEFFICIENT(20.0),
    HAMMING_DISTANCE(10.0),
    JACCARD_SIMILARITY(20.0),
    JARO_WINKLER_DISTANCE(20.0)
}