package com.guild.kotlin.adventurer.entities

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "participates", schema = "public")
open class Participate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Group_id")
    open var group: Group? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Adventurer_id")
    open var adventurer: User? = null

    @Lob
    @Column(name = "Role")
    open var role: String? = null

    @Column(name = "Date_joined")
    open var dateJoined: LocalDate? = null

    @Column(name = "Date_left")
    open var dateLeft: LocalDate? = null

    constructor(
        id: Long?,
        group: Group?,
        adventurer: User?,
        role: String?,
        dateJoined: LocalDate?,
        dateLeft: LocalDate?
    ) {
        this.id = id
        this.group = group
        this.adventurer = adventurer
        this.role = role
        this.dateJoined = dateJoined
        this.dateLeft = dateLeft
    }

    override fun toString(): String {
        return "Participate(id=$id, group=$group, adventurer=$adventurer, role=$role, dateJoined=$dateJoined, dateLeft=$dateLeft)"
    }
}