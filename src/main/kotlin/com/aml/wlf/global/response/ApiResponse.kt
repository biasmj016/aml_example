package com.aml.wlf.global.response

import java.time.LocalDateTime

data class ApiResponse(
    val message: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        fun  success(): ApiResponse = ApiResponse("success", LocalDateTime.now())
        fun  success(message: String): ApiResponse= ApiResponse(message, LocalDateTime.now())
        fun  fail(message: String): ApiResponse= ApiResponse(message, LocalDateTime.now())
    }
}