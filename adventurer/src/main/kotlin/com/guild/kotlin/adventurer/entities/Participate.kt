package com.guild.kotlin.adventurer.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.Type
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "participates", schema = "public")
open class Participate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "jobs", "participates", "photos")
    @JoinColumn(name = "Group_id")
    open var group: Group? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "adventurer", "guildStaff", "jobs", "orders", "reviews", "participates", "photos")
    @JoinColumn(name = "Adventurer_id")
    open var adventurer: User? = null

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "Role", nullable = false)
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

    constructor() {
    }

    override fun toString(): String {
        return "Participate(id=$id, group=$group, adventurer=$adventurer, role=$role, dateJoined=$dateJoined, dateLeft=$dateLeft)"
    }
}