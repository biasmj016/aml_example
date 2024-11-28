package com.aml.wlf.algorithms.application.port.`in`.service

import com.aml.wlf.algorithms.application.port.`in`.usecase.NameSimilarity
import com.aml.wlf.algorithms.infrastructure.`in`.web.response.SimilarityResponse
import com.aml.wlf.watchlist.application.port.`in`.usecase.FetchWatchlist
import org.springframework.stereotype.Service
import java.time.LocalDate

interface ScoreSimilarity {
    fun execute(base: String, countryCode: String, birthDate: LocalDate): SimilarityResponse

    @Service
    class ScoreSimilarityClass(
        private val fetchWatchlistUsecase: FetchWatchlist.FetchWatchlistUsecase,
        private val nameSimilarityUsecase: NameSimilarity.NameSimilarityUsecase
    ) : ScoreSimilarity {

        override fun execute(
            base: String,
            countryCode: String,
            birthDate: LocalDate
        ): SimilarityResponse {
            require(base.isNotBlank()) { "Base name cannot be blank." }
            require(countryCode.isNotBlank()) { "Country code cannot be blank." }
            require(!birthDate.isAfter(LocalDate.now())) { "Birth date cannot be in the future." }

            val watchlist = fetchWatchlistUsecase.fetch(countryCode, birthDate)

            if (watchlist.isEmpty()) {
                throw IllegalArgumentException("No matching entries found in the watchlist.")
            }

            val mostSimilar = watchlist
                .map { it.name to nameSimilarityUsecase.calculate(base, it.name) }
                .maxByOrNull { it.second }
                ?: Pair(null, 0.0)

            return SimilarityResponse(
                baseName = base,
                similarName = mostSimilar.first,
                similarityPercentage = mostSimilar.second,
                isSamePerson = mostSimilar.second >= 0.9
            )
        }
    }
}