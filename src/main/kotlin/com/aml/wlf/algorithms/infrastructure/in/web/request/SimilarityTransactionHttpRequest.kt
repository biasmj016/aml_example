package com.aml.wlf.algorithms.infrastructure.`in`.web.request

import java.time.LocalDate

data class SimilarityTransactionHttpRequest(
    val baseName: String,
    val countryCode: String,
    val birthDate: LocalDate
) {
    init {
        require(baseName.isNotBlank()) { "Base name cannot be blank." }
        require(countryCode.isNotBlank()) { "Country code cannot be blank." }
        require(!birthDate.isAfter(LocalDate.now())) { "Birth date cannot be in the future." }
    }
}