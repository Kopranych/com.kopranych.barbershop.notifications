package com.kopranych.barbershop.notifications.model

import java.time.LocalDate

interface Payload {
  val id: Long
}

data class ClientBirthdayPayload(
    override val id: Long,
    val birthday: LocalDate
) : Payload {
}