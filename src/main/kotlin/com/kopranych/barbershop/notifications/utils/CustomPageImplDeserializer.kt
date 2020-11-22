package com.kopranych.barbershop.notifications.utils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.kopranych.barbershop.notifications.model.CustomPageImpl
import com.kopranych.barbershop.notifications.model.dto.ClientDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import java.time.LocalDate


class CustomPageImplDeserializer : StdDeserializer<Page<ClientDto>> {
  constructor() : this(null)
  constructor(vc: Class<Any>?) : super(vc)

  override fun deserialize(jasonParser: JsonParser, ctx: DeserializationContext): Page<ClientDto>? {
    val jsonNode: JsonNode = jasonParser.codec.readTree(jasonParser)

    val contentNode: JsonNode = jsonNode["content"]
    val content = mutableListOf<ClientDto>()

    for (client in contentNode) {
      content.add(ClientDto(
          client["id"].longValue() as Long,
          client["name"].textValue(),
          client["surname"].textValue(),
          client["middleName"].textValue(),
          client["phone"].textValue(),
          client["email"].textValue(),
          LocalDate.parse(client["birthday"].textValue()),
          client["fullName"].textValue(),
      ))
    }

    val total: Long = jsonNode["totalElements"].longValue()

    //TODO: Add Sort if need
    val pageableNode: JsonNode = jsonNode["pageable"]
    val page = pageableNode["pageNumber"].numberValue() as Int
    val size = pageableNode["pageSize"].numberValue() as Int

    return CustomPageImpl(content, PageRequest.of(page, size, ), total)
  }

}
