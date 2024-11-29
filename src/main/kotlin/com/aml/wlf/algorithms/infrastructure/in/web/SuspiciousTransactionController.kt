package com.aml.wlf.algorithms.infrastructure.`in`.web

import com.aml.wlf.algorithms.application.port.`in`.service.SuspiciousTransactionFilter
import com.aml.wlf.algorithms.infrastructure.`in`.web.request.SimilarityTransactionHttpRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SuspiciousTransactionController (
    private val suspiciousTransactionFilterService: SuspiciousTransactionFilter.SuspiciousTransactionFilterService
) {

    @PostMapping("/api/transactions/wlf")
    fun wlf(@RequestBody request: SimilarityTransactionHttpRequest) {
        suspiciousTransactionFilterService.execute(request.baseName, request.countryCode, request.birthDate)
    }
}