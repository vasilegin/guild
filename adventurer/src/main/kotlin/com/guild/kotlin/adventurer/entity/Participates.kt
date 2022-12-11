package com.guild.kotlin.adventurer.entity

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.sql.Date


@Entity
@Table(name = "participates")
class Participates {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    var id: Long = 0

    @ManyToOne
    @JoinColumn(name = "Group_id")
    var group: Groups? = null

    @ManyToOne
    @JoinColumn(name = "Adventurer_id")
    var adventurer: Users? = null

    @Column(name = "Role")
    var role: String? = null

    @Column(name = "Date_joined")
    var date_joined: Date? = null

    @Column(name = "Date_left")
    var date_left: Date? = null

    constructor(id: Long, group: Groups?, adventurer: Users?, role: String?, date_joined: Date?, date_left: Date?) {
        this.id = id
        this.group = group
        this.adventurer = adventurer
        this.role = role
        this.date_joined = date_joined
        this.date_left = date_left
    }

    override fun toString(): String {
        return "Participates(id=$id, group=$group, adventurer=$adventurer, role=$role, date_joined=$date_joined, date_left=$date_left)"
    }
}