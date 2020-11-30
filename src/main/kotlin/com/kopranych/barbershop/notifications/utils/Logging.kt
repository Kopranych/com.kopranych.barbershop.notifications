package com.kopranych.barbershop.notifications.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface Logging {
  val log: Logger
    get() = LoggerFactory.getLogger(javaClass)

}