package com.kopranych.barbershop.notifications.config

import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@EnableDiscoveryClient
@Component
class CloudConfig {

  @LoadBalanced
  @Bean
  fun restTemplate(): RestTemplate {
      return RestTemplate()
  }
}