package com.aml.wlf.watchlist.infrastructure.`in`.web.request

import com.aml.wlf.watchlist.domain.Watchlist
import java.time.LocalDate

data class WatchlistHttpRequest(
    val name: String,
    val birthDate: LocalDate,
    val countryCode: String
) {
    init {
        require(name.isNotBlank()) { "Base name cannot be blank." }
        require(countryCode.isNotBlank()) { "Country code cannot be blank." }
        require(!birthDate.isAfter(LocalDate.now())) { "Birth date cannot be in the future." }
    }

    fun toDomain(): Watchlist {
        return Watchlist(null, name, birthDate, countryCode)
    }
}