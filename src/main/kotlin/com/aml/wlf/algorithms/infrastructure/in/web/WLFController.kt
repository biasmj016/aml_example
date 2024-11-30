package com.aml.wlf.algorithms.infrastructure.`in`.web

import com.aml.wlf.algorithms.application.port.`in`.service.SuspiciousTransactionFilter
import com.aml.wlf.algorithms.infrastructure.`in`.web.request.SimilarityTransactionHttpRequest
import com.aml.wlf.global.response.ApiResponse
import com.aml.wlf.global.response.ApiResponse.Companion.success
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class WLFController(
    private val suspiciousTransactionFilterService: SuspiciousTransactionFilter.SuspiciousTransactionFilterService
) {

    @PostMapping("/api/transactions/wlf")
    fun wlf(@RequestBody request: SimilarityTransactionHttpRequest): ApiResponse {
        suspiciousTransactionFilterService.execute(request.baseName, request.countryCode, request.birthDate)
        return success("Request completed. The item is being filtered.")
    }
}