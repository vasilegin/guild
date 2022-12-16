package com.guild.kotlin.adventurer.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "guild_staff", schema = "public")
open class GuildStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "Position")
    open var position: String? = null

    @OneToMany(mappedBy = "guildStaff")
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "adventurer", "guildStaff", "jobs", "orders", "reviews", "participates", "photos")
    open var users: MutableSet<User> = mutableSetOf()

    constructor(id: Long?, position: String?) {
        this.id = id
        this.position = position
    }

    override fun toString(): String {
        return "GuildStaff(id=$id, position=$position)"
    }
}