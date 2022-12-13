package com.guild.kotlin.adventurer.entities

import javax.persistence.*

@Entity
@Table(name = "groups", schema = "public")
open class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Lob
    @Column(name = "Name")
    open var name: String? = null

    @Column(name = "Active")
    open var active: Boolean? = null

    @OneToMany(mappedBy = "group")
    open var jobs: MutableSet<Job> = mutableSetOf()

    @OneToMany(mappedBy = "group")
    open var participates: MutableSet<Participate> = mutableSetOf()

    @OneToMany(mappedBy = "group")
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

    override fun toString(): String {
        return "Group(id=$id, name=$name, active=$active)"
    }
}