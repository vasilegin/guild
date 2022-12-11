package com.guild.kotlin.adventurer.entity

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "guild_staff")
class Guild_staff {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "Position")
    var position: String? = null

    constructor(id: Long, position: String?) {
        this.id = id
        this.position = position
    }

    override fun toString(): String {
        return "Guild_staff(id=$id, position=$position)"
    }
}