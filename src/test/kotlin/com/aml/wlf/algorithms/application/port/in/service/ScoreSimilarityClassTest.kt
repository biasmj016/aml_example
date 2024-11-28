package com.aml.wlf.algorithms.application.port.`in`.service

import com.aml.wlf.algorithms.application.port.`in`.usecase.NameSimilarity
import com.aml.wlf.watchlist.application.port.`in`.usecase.FetchWatchlist
import com.aml.wlf.watchlist.domain.Watchlist
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import java.time.LocalDate
import kotlin.test.Test

class ScoreSimilarityClassTest {

    private val fetchWatchlistUsecase = Mockito.mock(FetchWatchlist.FetchWatchlistUsecase::class.java)
    private val nameSimilarityUsecase = Mockito.mock(NameSimilarity.NameSimilarityUsecase::class.java)

    private val scoreSimilarityClass =
        ScoreSimilarity.ScoreSimilarityClass(fetchWatchlistUsecase, nameSimilarityUsecase)

    @Test
    fun findMostSimilarName_low() {
        val baseName = "John Doe"
        val watchlist = listOf(Watchlist(2L, "Jane Doe",  LocalDate.of(1990, 1, 1), "BGD"))

        Mockito.`when`(fetchWatchlistUsecase.fetch("BGD", LocalDate.of(1990, 1, 1))).thenReturn(watchlist)
        Mockito.`when`(nameSimilarityUsecase.calculate(baseName, "Jane Doe")).thenReturn(0.3)

        val result = scoreSimilarityClass.execute(baseName, "BGD", LocalDate.of(1990, 1, 1))
        assertFalse(result.isSamePerson)
    }

    @Test
    fun findMostSimilarName() {
        val baseName = "John Doe"
        val watchlist = listOf(Watchlist(2L, "John Doe", LocalDate.of(1990, 1, 1), "BGD"))

        Mockito.`when`(fetchWatchlistUsecase.fetch("BGD", LocalDate.of(1990, 1, 1))).thenReturn(watchlist)
        Mockito.`when`(nameSimilarityUsecase.calculate(baseName, "John Doe")).thenReturn(1.0)

        val result = scoreSimilarityClass.execute(baseName, "BGD", LocalDate.of(1990, 1, 1))
        assertTrue(result.isSamePerson)
    }

    @Test
    fun validate_exception() {
        assertThrows<IllegalArgumentException> {
            scoreSimilarityClass.execute("test", "", LocalDate.of(1990, 1, 1))
        }
    }
}