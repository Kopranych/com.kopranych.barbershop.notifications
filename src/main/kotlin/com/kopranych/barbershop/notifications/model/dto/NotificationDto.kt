package com.kopranych.barbershop.notifications.model.dto

import com.kopranych.barbershop.notifications.model.Payload
import java.time.Instant

class NotificationDto(
    val id: Long,
    val data: Instant,
    val payload: Payload
) {

}
