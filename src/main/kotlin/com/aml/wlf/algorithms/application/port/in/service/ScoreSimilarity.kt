package com.aml.wlf.algorithms.application.port.`in`.service

import com.aml.wlf.algorithms.application.port.`in`.service.request.SimilarityRequest
import com.aml.wlf.algorithms.application.port.`in`.usecase.NameSimilarity
import com.aml.wlf.algorithms.application.port.`in`.service.response.SimilarityResponse
import com.aml.wlf.watchlist.application.port.`in`.usecase.FetchWatchlist
import org.springframework.stereotype.Service
import java.time.LocalDate

interface ScoreSimilarity {
    fun execute(request: SimilarityRequest): SimilarityResponse

    @Service
    class ScoreSimilarityService(
        private val fetchWatchlistUsecase: FetchWatchlist.FetchWatchlistUsecase,
        private val nameSimilarityUsecase: NameSimilarity.NameSimilarityUsecase
    ) : ScoreSimilarity {

        override fun execute(request: SimilarityRequest): SimilarityResponse {
            val watchlist = fetchWatchlistUsecase.fetch(request.countryCode, request.birthDate)

            if (watchlist.isEmpty()) {
                throw IllegalArgumentException("No matching entries found in the watchlist.")
            }

            val mostSimilar = watchlist
                .map { it.name to nameSimilarityUsecase.calculate(request.baseName, it.name) }
                .maxByOrNull { it.second } ?: Pair(null, 0.0)

            return SimilarityResponse(
                baseName = request.baseName,
                countryCode = request.countryCode,
                birthDate = request.birthDate,
                similarName = mostSimilar.first,
                similarityPercentage = mostSimilar.second,
                isSamePerson = mostSimilar.second >= 90.0
            )
        }
    }
}