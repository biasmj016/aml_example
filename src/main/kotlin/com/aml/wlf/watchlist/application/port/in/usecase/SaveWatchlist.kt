package com.aml.wlf.watchlist.application.port.`in`.usecase

import com.aml.wlf.watchlist.application.port.out.WatchlistRepository
import com.aml.wlf.watchlist.domain.Watchlist
import org.springframework.stereotype.Component

interface SaveWatchlist {
    fun save(watchlist: Watchlist): Watchlist

    @Component
    class SaveWatchlistUsecase(
        private val repository: WatchlistRepository
    ) : SaveWatchlist {

            override fun save(watchlist: Watchlist): Watchlist {
                return repository.save(watchlist)
            }
    }
}