package com.guild.kotlin.adventurer.entities

import javax.persistence.*

@Entity
@Table(name = "guild_staff", schema = "public")
open class GuildStaff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Lob
    @Column(name = "Position")
    open var position: String? = null

    @OneToMany(mappedBy = "guildStaff")
    open var users: MutableSet<User> = mutableSetOf()

    constructor(id: Long?, position: String?, users: MutableSet<User>) {
        this.id = id
        this.position = position
        this.users = users
    }

    override fun toString(): String {
        return "GuildStaff(id=$id, position=$position, users=$users)"
    }
}