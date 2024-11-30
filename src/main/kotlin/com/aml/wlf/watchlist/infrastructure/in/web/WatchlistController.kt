package com.aml.wlf.watchlist.infrastructure.`in`.web

import com.aml.wlf.global.response.ApiResponse
import com.aml.wlf.watchlist.application.port.`in`.usecase.SaveWatchlist
import com.aml.wlf.watchlist.infrastructure.`in`.web.request.WatchlistHttpRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class WatchlistController(
    private val saveWatchlist: SaveWatchlist
) {

    @PostMapping("/api/watchlist")
    fun add(@RequestBody request: WatchlistHttpRequest): ApiResponse {
        saveWatchlist.save(request.toDomain())
        return ApiResponse.success()
    }
}