package com.aml.wlf.watchlist.application.port.`in`

import com.aml.wlf.watchlist.application.port.out.WatchlistRepository
import com.aml.wlf.watchlist.domain.Watchlist
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

interface SaveWatchlist {
    fun save(watchlist: Watchlist): Watchlist

    @Service
    @Transactional
    class SaveWatchlistUsecase(
        private val repository: WatchlistRepository
    ) : SaveWatchlist {

            override fun save(watchlist: Watchlist): Watchlist {
                return repository.save(watchlist)
            }
    }
}