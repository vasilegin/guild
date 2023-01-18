package com.guild.kotlin.adventurer.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SpringSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private val tokenProvider: JwtTokenProvider? = null
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }


    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun corsConfigurer(): WebMvcConfigurer? {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/rest/**").allowedMethods("*").allowedHeaders("*");
            }
        }
    }

//    @Bean
//    fun corsFilter(): CorsFilter? {
//        val source = UrlBasedCorsConfigurationSource()
//        val config = CorsConfiguration()
//        config.addAllowedOrigin("*")
//        config.addAllowedHeader("*")
//        config.addAllowedMethod("*")
//        source.registerCorsConfiguration("/**", config)
//        return CorsFilter(source)
//    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.cors()
            .and()
        http.csrf()
            .disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/user/**").permitAll()
//            .antMatchers("/swagger-ui/**").permitAll()
//            .antMatchers("/v3/api-docs/**").permitAll()
//            .antMatchers("/swagger-ui.html").permitAll()
            .anyRequest().authenticated()

        http.apply(JwtTokenConfigurer(tokenProvider!!))
    }
}