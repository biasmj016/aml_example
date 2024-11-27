package com.aml.wlf.watchlist.domain

import java.time.LocalDate

data class Watchlist(
    val id: Long?,
    val name: String,
    val birthDate: LocalDate?,
    val countryCode: String?
)