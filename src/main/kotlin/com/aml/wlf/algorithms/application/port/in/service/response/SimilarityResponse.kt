package com.aml.wlf.algorithms.application.port.`in`.service.response

import java.time.LocalDate

data class SimilarityResponse(
    val baseName: String,
    val countryCode: String,
    val birthDate: LocalDate,
    val similarName: String?,
    val similarityPercentage: Double,
    val isSamePerson: Boolean
) {
    override fun toString(): String {
        return """
            Similarity Report:
            - Base Name: $baseName
            - Country Code: $countryCode
            - Birth Date: $birthDate
            - Similar Name: ${similarName ?: "N/A"}
            - Similarity Percentage: $similarityPercentage
            - Is Same Person: $isSamePerson
        """.trimIndent()
    }
}