package com.guild.kotlin.adventurer.config

import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtTokenConfigurer(private val tokenProvider: JwtTokenProvider) :
    SecurityConfigurerAdapter<DefaultSecurityFilterChain?, HttpSecurity>() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.addFilterBefore(JwtTokenFilter(tokenProvider), UsernamePasswordAuthenticationFilter::class.java)
    }
}