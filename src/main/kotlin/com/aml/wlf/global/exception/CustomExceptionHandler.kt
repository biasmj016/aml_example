package com.aml.wlf.global.exception

import com.aml.wlf.global.response.ApiResponse
import com.aml.wlf.global.response.ApiResponse.Companion.fail
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.sql.SQLException


@RestControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<ApiResponse> {
        println("[HttpMessageNotReadableException] contents: ${ex.message}")
        return ResponseEntity.badRequest().body(fail(ex.message ?: "Malformed request"))
    }

    @ExceptionHandler(NoSuchElementException::class, IllegalArgumentException::class)
    fun handleInvalidRequestException(ex: Exception): ResponseEntity<ApiResponse> {
        println("[${ex.javaClass.simpleName}] contents: ${ex.message}")
        return ResponseEntity.badRequest().body(fail(ex.message ?: "Invalid request"))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ApiResponse> {
        println("[MethodArgumentNotValidException] contents: ${ex.message}")
        return ResponseEntity.badRequest().body(fail(ex.message ?: "Validation error"))
    }

    @ExceptionHandler(SQLException::class)
    fun handleSQLException(ex: SQLException): ResponseEntity<ApiResponse> {
        println("[SQLException] contents: ${ex.message}")
        return ResponseEntity.internalServerError().body(fail(ex.message ?: "Database error"))
    }
}