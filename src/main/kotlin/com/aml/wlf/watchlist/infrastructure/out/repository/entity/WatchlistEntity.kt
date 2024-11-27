package com.aml.wlf.watchlist.infrastructure.out.repository.entity

import com.aml.wlf.watchlist.domain.Watchlist
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "watchlist")
data class WatchlistEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
    private val name: String,
    private val birthDate: LocalDate? = null,
    @Column(length = 3)
    private val countryCode: String? = null,
    private val updatedAt: LocalDateTime = LocalDateTime.now()
) {

    constructor() : this(
        id = null,
        name = "",
        birthDate = null,
        countryCode = null,
        updatedAt = LocalDateTime.now()
    )

    constructor(watchlist: Watchlist) : this(
        id = null,
        name = watchlist.name,
        birthDate = watchlist.birthDate,
        countryCode = watchlist.countryCode,
        updatedAt = LocalDateTime.now()
    )

    fun toDomain(): Watchlist {
        return Watchlist(id ?: 0L, name, birthDate, countryCode)
    }
}