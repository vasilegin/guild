package com.guild.kotlin.adventurer.config

import com.guild.kotlin.adventurer.entities.Role
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.*
import javax.annotation.PostConstruct


@Component
class JwtTokenProvider : Serializable {
    @Value("\${jwt.secret-key}")
    private var secretKey: String? = null
    @PostConstruct
    protected fun init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey!!.toByteArray())
    }

    private val validityInMilliseconds = (3600000 * 55555 * 3600000).toLong()

    fun createToken(username: String?, role: String): String {
        val claims = Jwts.claims().setSubject(username)
        claims["auth"] = role
        val now = Date()
        return Jwts.builder().setClaims(claims).setIssuedAt(now)
            .setExpiration(Date(now.time + validityInMilliseconds))
            .signWith(SignatureAlgorithm.HS256, secretKey).compact()
    }

    @Autowired
    private val userDetailsService: UserDetailsService? = null
    fun getAuthentication(username: String?): Authentication {
        val userDetails = userDetailsService!!.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(
            userDetails.username, userDetails.password,
            userDetails.authorities
        )
    }

    fun getClaimsFromToken(token: String?): Claims {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body
    }

    companion object {
        private const val serialVersionUID = 2569800841756370596L
    }
}