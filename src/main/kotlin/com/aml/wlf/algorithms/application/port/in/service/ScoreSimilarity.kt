package com.aml.wlf.algorithms.application.port.`in`.service

import com.aml.wlf.algorithms.application.port.`in`.service.request.SimilarityRequest
import com.aml.wlf.algorithms.application.port.`in`.service.response.SimilarityResponse
import com.aml.wlf.watchlist.application.port.`in`.usecase.FetchWatchlist
import com.aml.wlf.algorithms.application.port.`in`.usecase.NameSimilarity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

interface ScoreSimilarity {
    suspend fun execute(request: SimilarityRequest): SimilarityResponse

    @Service
    class ScoreSimilarityService(
        private val fetchWatchlistUsecase: FetchWatchlist.FetchWatchlistUsecase,
        private val nameSimilarityUsecase: NameSimilarity.NameSimilarityUsecase
    ) : ScoreSimilarity {

        override suspend fun execute(request: SimilarityRequest): SimilarityResponse {
            return withContext(Dispatchers.IO) {
                val mostSimilar = fetchWatchlistUsecase.fetch(request.countryCode, request.birthDate)
                    .map { it.name to nameSimilarityUsecase.calculate(request.baseName, it.name) }
                    .maxByOrNull { it.second } ?: Pair(null, 0.0)

                SimilarityResponse(
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
}