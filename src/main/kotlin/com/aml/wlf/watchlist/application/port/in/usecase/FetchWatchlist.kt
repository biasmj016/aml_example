package com.aml.wlf.watchlist.application.port.`in`.usecase

import com.aml.wlf.watchlist.application.port.out.WatchlistRepository
import com.aml.wlf.watchlist.domain.Watchlist
import org.springframework.stereotype.Component
import java.time.LocalDate

interface FetchWatchlist {
    fun fetch(countryCode:String, birthDate: LocalDate): List<Watchlist>

    @Component
    class FetchWatchlistUsecase(
        private val repository: WatchlistRepository
    ) : FetchWatchlist {

        override fun fetch(countryCode:String, birthDate: LocalDate): List<Watchlist> {
            return repository.findAll(countryCode, birthDate)
        }
    }
}