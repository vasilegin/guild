package com.guild.kotlin.adventurer.entity

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator


@Entity
@Table(name = "adventurer")
class Adventurer {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "Rank")
    var rank: String? = null

    @Column(name = "Role")
    var role: String? = null

    @Column(name = "Weapon")
    var weapon: String? = null

    constructor(id: Long, rank: String?, role: String?, weapon: String?) {
        this.id = id
        this.rank = rank
        this.role = role
        this.weapon = weapon
    }

    override fun toString(): String {
        return "Adventurer(id=$id, rank=$rank, role=$role, weapon=$weapon)"
    }
}