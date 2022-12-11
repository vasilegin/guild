package com.guild.kotlin.adventurer.entity

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.sql.Date


@Entity
@Table(name = "participates")
class Participates {

    @Column(name = "Group_id")
    var group: Groups? = null

    @Column(name = "Adventurer_id")
    var adventurer: Users? = null

    @Column(name = "Role")
    var role: Groups? = null

    @Column(name = "Date_joined")
    var date_joined: Date? = null

    @Column(name = "Date_left")
    var date_left: Date? = null

    constructor(group: Groups?, adventurer: Users?, role: Groups?, date_joined: Date?, date_left: Date?) {
        this.group = group
        this.adventurer = adventurer
        this.role = role
        this.date_joined = date_joined
        this.date_left = date_left
    }

    override fun toString(): String {
        return "Participates(group=$group, adventurer=$adventurer, role=$role, date_joined=$date_joined, date_left=$date_left)"
    }
}