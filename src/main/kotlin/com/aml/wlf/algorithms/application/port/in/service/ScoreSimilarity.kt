package com.aml.wlf.algorithms.application.port.`in`.service

import com.aml.wlf.algorithms.application.port.`in`.usecase.NameSimilarity
import com.aml.wlf.watchlist.application.port.`in`.usecase.FetchWatchlist
import org.springframework.stereotype.Service
import java.time.LocalDate

interface ScoreSimilarity {
    fun findMostSimilarName(base: String, countryCode: String, birthDate: LocalDate): Pair<String, Double>?
}

@Service
class ScoreSimilarityClass(
    private val fetchWatchlistUsecase: FetchWatchlist.FetchWatchlistUsecase,
    private val nameSimilarityUsecase: NameSimilarity.NameSimilarityUsecase
) : ScoreSimilarity {

    override fun findMostSimilarName(base: String, countryCode: String, birthDate: LocalDate): Pair<String, Double>? {
        val watchlist = fetchWatchlistUsecase.fetch(countryCode, birthDate)

        return watchlist
            .map { it.name to nameSimilarityUsecase.calculate(base, it.name) }
            .maxByOrNull { it.second }
    }
}