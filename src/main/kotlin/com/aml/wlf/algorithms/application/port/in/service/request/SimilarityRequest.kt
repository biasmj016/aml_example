package com.aml.wlf.algorithms.application.port.`in`.service.request

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class SimilarityRequest @JsonCreator constructor(
    @JsonProperty("baseName") val baseName: String,
    @JsonProperty("countryCode") val countryCode: String,
    @JsonProperty("birthDate") val birthDate: LocalDate
) {
    init {
        require(baseName.isNotBlank()) { "Base name cannot be blank." }
        require(countryCode.isNotBlank()) { "Country code cannot be blank." }
        require(!birthDate.isAfter(LocalDate.now())) { "Birth date cannot be in the future." }
    }
}