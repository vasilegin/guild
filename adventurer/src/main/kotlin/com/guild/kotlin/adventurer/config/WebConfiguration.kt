package com.guild.kotlin.adventurer.config

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfiguration : WebMvcConfigurer {
    private val log = LoggerFactory.getLogger(WebConfiguration::class.java)

    override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("*")
            .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");

    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        log.info("addResourceHandlers called")
        registry.addResourceHandler("/static/**")
            .addResourceLocations("classpath:/static/")
    }
    override fun addViewControllers(registry: ViewControllerRegistry) {
        log.info("addViewControllers called")
        registry.addViewController("/").setViewName("forward:/index.html")
    }
}