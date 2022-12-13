package com.guild.kotlin.adventurer.entities

import com.guild.kotlin.adventurer.entities.User
import javax.persistence.*

@Entity
@Table(name = "adventurer", schema = "public")
open class Adventurer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Lob
    @Column(name = "\"Rank\"")
    open var rank: String? = null

    @Lob
    @Column(name = "\"Role\"")
    open var role: String? = null

    @Lob
    @Column(name = "\"Weapon\"")
    open var weapon: String? = null

    @OneToMany(mappedBy = "adventurer")
    open var users: MutableSet<User> = mutableSetOf()

    constructor(id: Long?, rank: String?, role: String?, weapon: String?) {
        this.id = id
        this.rank = rank
        this.role = role
        this.weapon = weapon
    }

    override fun toString(): String {
        return "Adventurer(id=$id, rank=$rank, role=$role, weapon=$weapon)"
    }
}