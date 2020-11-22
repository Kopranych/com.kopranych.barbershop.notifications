package com.kopranych.barbershop.notifications.service

import com.kopranych.barbershop.notifications.client.ClientsClient
import com.kopranych.barbershop.notifications.model.ClientBirthdayPayload
import com.kopranych.barbershop.notifications.model.dto.NotificationDto
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class NotificationsService(private val clientsClient: ClientsClient) {

  fun getClientsBirthdayNotification(page: Int, size: Int): Page<NotificationDto>? {
    return clientsClient.getAllClients(page, size)
        ?.map{ NotificationDto(it.id, Instant.now(), ClientBirthdayPayload(it.id, it.birthday)) }
  }
}
