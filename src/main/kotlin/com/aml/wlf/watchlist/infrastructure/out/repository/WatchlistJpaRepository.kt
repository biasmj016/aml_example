package com.aml.wlf.watchlist.infrastructure.out.repository

import com.aml.wlf.watchlist.infrastructure.out.repository.entity.WatchlistEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface WatchlistJpaRepository : JpaRepository<WatchlistEntity, Long> {
    fun findAllByCountryCodeAndBirthDate(countryCode: String, birthDate: LocalDate): List<WatchlistEntity>
}