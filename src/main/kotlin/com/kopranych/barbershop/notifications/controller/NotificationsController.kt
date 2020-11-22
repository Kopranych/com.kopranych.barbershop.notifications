package com.kopranych.barbershop.notifications.controller

import com.kopranych.barbershop.notifications.model.dto.NotificationDto
import com.kopranych.barbershop.notifications.service.NotificationsService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class NotificationsController(
    private val notificationsService: NotificationsService
) : BaseController() {

  @GetMapping("/clients/birthday")
  fun getClientsBirthday(
      @RequestParam(defaultValue = "0") page: Int,
      @RequestParam(defaultValue = "100") size: Int
  ): Page<NotificationDto>? {
    return notificationsService.getClientsBirthdayNotification(page, size)
  }

}