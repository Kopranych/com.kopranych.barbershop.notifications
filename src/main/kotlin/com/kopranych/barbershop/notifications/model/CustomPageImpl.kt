package com.kopranych.barbershop.notifications.model

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.kopranych.barbershop.notifications.utils.CustomPageImplDeserializer
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

@JsonDeserialize(using = CustomPageImplDeserializer::class)
class CustomPageImpl<T>(content: MutableList<T>, pageable: Pageable, total: Long)
  : PageImpl<T>(content, pageable, total) {
}

