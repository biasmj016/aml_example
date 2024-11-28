package com.aml.wlf.algorithms.application.port.`in`.service

import com.aml.wlf.algorithms.application.port.`in`.usecase.NameSimilarity
import com.aml.wlf.watchlist.application.port.`in`.usecase.FetchWatchlist
import com.aml.wlf.watchlist.domain.Watchlist
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.Mockito
import java.time.LocalDate
import kotlin.test.Test

class ScoreSimilarityClassTest {

    private val fetchWatchlistUsecase = Mockito.mock(FetchWatchlist.FetchWatchlistUsecase::class.java)
    private val nameSimilarityUsecase = Mockito.mock(NameSimilarity.NameSimilarityUsecase::class.java)

    private val scoreSimilarityClass =
        ScoreSimilarityClass(fetchWatchlistUsecase, nameSimilarityUsecase)

    @Test
    fun findMostSimilarName_low() {
        val baseName = "John Doe"
        val watchlist = listOf(Watchlist(2L, "Jane Doe",  LocalDate.of(1990, 1, 1), "BGD"))

        Mockito.`when`(fetchWatchlistUsecase.fetch("BGD", LocalDate.of(1990, 1, 1))).thenReturn(watchlist)
        Mockito.`when`(nameSimilarityUsecase.calculate(baseName, "Jane Doe")).thenReturn(0.3)

        val result = scoreSimilarityClass.findMostSimilarName(baseName, "BGD", LocalDate.of(1990, 1, 1))
        assertEquals("Jane Doe" to 0.3, result)
    }

    @Test
    fun findMostSimilarName() {
        val baseName = "John Doe"
        val watchlist = listOf(Watchlist(2L, "John Doe", LocalDate.of(1990, 1, 1), "BGD"))

        Mockito.`when`(fetchWatchlistUsecase.fetch("BGD", LocalDate.of(1990, 1, 1))).thenReturn(watchlist)
        Mockito.`when`(nameSimilarityUsecase.calculate(baseName, "John Doe")).thenReturn(1.0)

        val result = scoreSimilarityClass.findMostSimilarName(baseName, "BGD", LocalDate.of(1990, 1, 1))
        assertEquals("John Doe" to 1.0, result)
    }
}