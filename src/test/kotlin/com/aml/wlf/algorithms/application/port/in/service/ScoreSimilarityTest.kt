package com.aml.wlf.algorithms.application.port.`in`.service
import com.aml.wlf.algorithms.application.port.`in`.service.request.SimilarityRequest
import com.aml.wlf.algorithms.application.port.`in`.usecase.NameSimilarity
import com.aml.wlf.watchlist.application.port.`in`.usecase.FetchWatchlist
import com.aml.wlf.watchlist.domain.Watchlist
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.mockito.Mockito
import java.time.LocalDate
import kotlin.test.Test

class ScoreSimilarityTest {

    private val fetchWatchlistUsecase = Mockito.mock(FetchWatchlist.FetchWatchlistUsecase::class.java)
    private val nameSimilarityUsecase = Mockito.mock(NameSimilarity.NameSimilarityUsecase::class.java)

    private val scoreSimilarityService =
        ScoreSimilarity.ScoreSimilarityService(fetchWatchlistUsecase, nameSimilarityUsecase)

    @Test
    fun findMostSimilarName_low() = runBlocking {
        val request = SimilarityRequest("John Doe", "BGD", LocalDate.of(1990, 1, 1))
        val watchlist = listOf(Watchlist(2L, "Jane Doe", LocalDate.of(1990, 1, 1), "BGD"))

        Mockito.`when`(fetchWatchlistUsecase.fetch("BGD", LocalDate.of(1990, 1, 1))).thenReturn(watchlist)
        Mockito.`when`(nameSimilarityUsecase.calculate("John Doe", "Jane Doe")).thenReturn(30.0)

        val result = scoreSimilarityService.execute(request)
        assertFalse(result.isSamePerson)
    }

    @Test
    fun findMostSimilarName() = runBlocking {
        val request = SimilarityRequest("John Doe", "BGD", LocalDate.of(1990, 1, 1))
        val watchlist = listOf(Watchlist(2L, "John Doe", LocalDate.of(1990, 1, 1), "BGD"))

        Mockito.`when`(fetchWatchlistUsecase.fetch("BGD", LocalDate.of(1990, 1, 1))).thenReturn(watchlist)
        Mockito.`when`(nameSimilarityUsecase.calculate("John Doe", "John Doe")).thenReturn(100.0)

        val result = scoreSimilarityService.execute(request)
        assertTrue(result.isSamePerson)
    }

}