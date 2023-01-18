package com.guild.kotlin.adventurer.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "groups")
open class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "Name", nullable = false)
    open var name: String? = null

    @Column(name = "Active", nullable = false)
    open var active: Boolean? = null

    @OneToMany(mappedBy = "group")
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "customer", "adventurer", "group", "reviews", "reports", "photos")
    open var jobs: MutableSet<Job> = mutableSetOf()

    @OneToMany(mappedBy = "group")
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "group", "adventurer")
    open var participates: MutableSet<Participate> = mutableSetOf()

    @OneToMany(mappedBy = "group")
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "job", "report", "review", "group", "user")
    open var photos: MutableSet<Photo> = mutableSetOf()

    constructor(
        id: Long?,
        name: String?,
        active: Boolean?,
    ) {
        this.id = id
        this.name = name
        this.active = active
    }

    constructor() {
    }

    override fun toString(): String {
        return "Group(id=$id, name=$name, active=$active)"
    }
}