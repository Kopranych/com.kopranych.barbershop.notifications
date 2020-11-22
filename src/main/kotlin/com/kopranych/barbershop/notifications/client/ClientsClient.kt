package com.kopranych.barbershop.notifications.client

import com.kopranych.barbershop.notifications.model.CustomPageImpl
import com.kopranych.barbershop.notifications.model.dto.ClientDto
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.core.ParameterizedTypeReference
import org.springframework.data.domain.Page
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.lang.String


@Component
class ClientsClient(private val restTemplate: RestTemplate, private val discoveryClient: DiscoveryClient) {


//  fun getAllClients(page: Int, size: Int): Page<ClientDto>? {
//    val queryParam = UriComponentsBuilder
//        .fromHttpUrl("http://clientsservice/api/clients")
//        .queryParam("page", page)
//        .queryParam("size", size)
//    val response = restTemplate.exchange(
//        "http://clientsservice/api/clients",
//        HttpMethod.GET,
//        null, object : ParameterizedTypeReference<Page<ClientDto>>() {}, queryParam
//    )
//    return response.body
//  }

  fun getAllClients(page: Int, size: Int): Page<ClientDto>? {
    val restTemplate = RestTemplate()
    val instances = discoveryClient.getInstances("clients")
    if (instances.size == 0) return null

    val serviceUri = String.format("%s/api/clients",
        instances[0].uri.toString())
    val queryParam = UriComponentsBuilder
        .fromHttpUrl(serviceUri)
        .queryParam("page", page)
        .queryParam("size", size)


    val restExchange: ResponseEntity<CustomPageImpl<ClientDto>> = restTemplate.exchange(
        serviceUri,
        HttpMethod.GET,
        null, object : ParameterizedTypeReference<CustomPageImpl<ClientDto>>() {}, queryParam)
    return restExchange.body
  }

}
