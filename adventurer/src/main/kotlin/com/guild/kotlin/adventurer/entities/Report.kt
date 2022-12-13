package com.guild.kotlin.adventurer.entities

import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "reports", schema = "public")
open class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Job_id")
    open var job: Job? = null

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "Text")
    open var text: String? = null

    @OneToMany(mappedBy = "report")
    open var photos: MutableSet<Photo> = mutableSetOf()

    constructor(id: Long?, job: Job?, text: String?) {
        this.id = id
        this.job = job
        this.text = text
    }

    override fun toString(): String {
        return "Report(id=$id, job=$job, text=$text)"
    }
}