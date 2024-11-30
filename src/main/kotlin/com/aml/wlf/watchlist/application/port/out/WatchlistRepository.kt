package com.aml.wlf.watchlist.application.port.out

import com.aml.wlf.watchlist.domain.Watchlist
import com.aml.wlf.watchlist.infrastructure.out.repository.WatchlistJpaRepository
import com.aml.wlf.watchlist.infrastructure.out.repository.entity.WatchlistEntity
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

interface WatchlistRepository {
    fun findAll(countryCode:String, birthDate: LocalDate): List<Watchlist>
    fun save(watchlist: Watchlist): Watchlist

    @Repository
    class WatchlistRepositoryImpl(
        private val jpaRepository: WatchlistJpaRepository
    ) : WatchlistRepository {

        @Transactional(readOnly = true)
        override fun findAll(countryCode: String, birthDate: LocalDate): List<Watchlist> {
            return jpaRepository.findAllByCountryCodeAndBirthDate(countryCode, birthDate)
                .map { it.toDomain() }
                .ifEmpty { throw IllegalArgumentException("No matching entries found in the watchlist.") }
        }

        @Transactional
        override fun save(watchlist: Watchlist): Watchlist {
            return jpaRepository.save(WatchlistEntity(watchlist)).toDomain()
        }
    }
}