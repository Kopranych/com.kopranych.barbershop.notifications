package com.kopranych.barbershop.notifications.client

import com.kopranych.barbershop.notifications.model.CustomPageImpl
import com.kopranych.barbershop.notifications.model.dto.ClientDto
import com.kopranych.barbershop.notifications.utils.Logging
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.springframework.core.ParameterizedTypeReference
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate


@Component
class ClientsClient(private val restTemplate: RestTemplate): Logging {

  @HystrixCommand(fallbackMethod = "fallbackGetAllClients")
  fun getAllClients(page: Int, size: Int): Page<ClientDto>? {
    val url = "http://clients/api/clients?page={page}&size={size}"
    val response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        null, object : ParameterizedTypeReference<CustomPageImpl<ClientDto>>() {}, page, size
    )
    return response.body
  }

  private fun fallbackGetAllClients(page: Int, size: Int): Page<ClientDto>?{
    log.warn("Fallback for getAllClients({}, {})", page, size)
    return CustomPageImpl(mutableListOf(), PageRequest.of(page, size), 0)
  }
}
