package com.guild.kotlin.adventurer.config

import org.json.JSONException
import org.json.JSONObject
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenFilter(private val tokenProvider: JwtTokenProvider) : OncePerRequestFilter() {
    @Throws(ServletException::class, IOException::class)
    public override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        log.info("JwtTokenFilter : doFilterInternal")
        val token = request.getHeader("Authorization")
        if (token != null) {
            try {
                val claims = tokenProvider.getClaimsFromToken(token)
                if (!claims.expiration.before(Date())) {
                    System.out.println(claims.subject);
                    val authentication = tokenProvider.getAuthentication(claims.subject)
                    System.out.println("2");
                    if (authentication.isAuthenticated) {
                        SecurityContextHolder.getContext().authentication = authentication
                    }
                }
            } catch (e: RuntimeException) {
                try {
                    SecurityContextHolder.clearContext()
                    response.contentType = "application/json"
                    response.status = HttpServletResponse.SC_UNAUTHORIZED
                    response.writer.println(
                        JSONObject().put("exception", "expired or invalid JWT token " + e.message)
                    )
                } catch (e1: IOException) {
                    e1.printStackTrace()
                } catch (e1: JSONException) {
                    e1.printStackTrace()
                }
                return
            }
        } else {
            log.info("first time so creating token using UserResourceImpl - authenticate method")
        }
        filterChain.doFilter(request, response)
    }

    companion object {
        private val log = LoggerFactory.getLogger(JwtTokenFilter::class.java)
    }
}