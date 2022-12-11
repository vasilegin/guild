package com.guild.kotlin.adventurer.entity

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "reports")
class Reports {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    var id: Long = 0

    @ManyToOne
    @JoinColumn(name = "Job_id")
    var job: Jobs? = null

    @Column(name = "Text")
    var text: String? = null

    constructor(id: Long, job: Jobs?, text: String?) {
        this.id = id
        this.job = job
        this.text = text
    }

    override fun toString(): String {
        return "Reports(id=$id, job=$job, text=$text)"
    }
}