package com.kopranych.barbershop.notifications.config

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@EnableCircuitBreaker
@Component
class CloudConfig {

  @LoadBalanced
  @Bean
  fun restTemplate(): RestTemplate {
      return RestTemplate()
  }
}