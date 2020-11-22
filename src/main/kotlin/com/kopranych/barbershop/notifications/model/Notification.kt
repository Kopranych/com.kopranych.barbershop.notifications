package com.kopranych.barbershop.notifications.model

import java.time.Instant

data class Notification(
    val id: Long,
    val data: Instant,
    val payload: Payload
) {
}