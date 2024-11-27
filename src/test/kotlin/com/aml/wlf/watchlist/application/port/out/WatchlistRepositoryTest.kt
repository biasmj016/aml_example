package com.aml.wlf.watchlist.application.port.out

import com.aml.wlf.watchlist.domain.Watchlist
import com.aml.wlf.watchlist.infrastructure.out.repository.WatchlistJpaRepository
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
@Transactional
class WatchlistRepositoryTest {

    @Autowired
    private lateinit var jpaRepository: WatchlistJpaRepository

    private lateinit var repository: WatchlistRepository.WatchlistRepositoryImpl

    @BeforeEach
    fun setUp() {
        repository = WatchlistRepository.WatchlistRepositoryImpl(jpaRepository)
    }

    @Test
    fun save() {
        val watchlist = Watchlist(null, "John", LocalDate.now(), "AFG")

        val result = repository.save(watchlist)
        println(result)
        assertNotNull(result.id)
    }

    @Test
    fun findAll() {
        repository.save(Watchlist(null, "ABC", LocalDate.now(), "AFG"))
        repository.save(Watchlist(null, "DEF", LocalDate.now().minusDays(1), "AFG"))
        repository.save(Watchlist(null, "GHI", LocalDate.now().minusDays(1), "AFG"))
        repository.save(Watchlist(null, "JKL", LocalDate.now().minusDays(1), "AFG"))

        val result = repository.findAll("AFG", LocalDate.now().minusDays(1))
        println(result)
        assertEquals(3, result.size)
    }
}