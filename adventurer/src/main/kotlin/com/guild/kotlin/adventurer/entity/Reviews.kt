package com.guild.kotlin.adventurer.entity

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
@Entity
@Table(name = "reviews")
class Reviews {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    var id: Long = 0

    @ManyToOne
    @JoinColumn(name = "Job_id")
    var job: Jobs? = null

    @ManyToOne
    @JoinColumn(name = "Author_id")
    var author: Users? = null

    @Column(name = "Text")
    var text: String? = null

    @Column(name = "Score")
    var score: Long? = null

    constructor(id: Long, job: Jobs?, author: Users?, text: String?, score: Long?) {
        this.id = id
        this.job = job
        this.author = author
        this.text = text
        this.score = score
    }

    override fun toString(): String {
        return "Reviews(id=$id, job=$job, author=$author, text=$text, score=$score)"
    }
}