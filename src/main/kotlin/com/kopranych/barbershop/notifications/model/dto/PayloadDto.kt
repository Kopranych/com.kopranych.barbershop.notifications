package com.kopranych.barbershop.notifications.model.dto

import java.time.LocalDate

interface PayloadDto {
  val id: Long
}

data class ClientBirthdayPayloadDto(
    override val id: Long,
    val birthday: LocalDate
) : PayloadDto {
}