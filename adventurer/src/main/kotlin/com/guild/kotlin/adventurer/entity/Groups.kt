package com.guild.kotlin.adventurer.entity

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator


@Entity
@Table(name = "groups")
class Groups {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "Name")
    var name: String? = null

    @Column(name = "Active")
    var active: String? = null

    constructor(id: Long, name: String?, active: String?) {
        this.id = id
        this.name = name
        this.active = active
    }

    override fun toString(): String {
        return "Groups(id=$id, name=$name, active=$active)"
    }
}