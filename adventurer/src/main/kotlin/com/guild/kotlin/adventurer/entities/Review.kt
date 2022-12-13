package com.guild.kotlin.adventurer.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "reviews", schema = "public")
open class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "customer", "adventurer", "group", "reviews", "reports", "photos")
    @JoinColumn(name = "Job_id")
    open var job: Job? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "adventurer", "guildStaff", "jobs", "orders", "reviews", "participates", "photos")
    @JoinColumn(name = "Author_id")
    open var author: User? = null

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "Text")
    open var text: String? = null

    @Column(name = "Score")
    open var score: Long? = null

    @OneToMany(mappedBy = "review")
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "job", "report", "review", "group", "user")
    open var photos: MutableSet<Photo> = mutableSetOf()

    constructor(id: Long?, job: Job?, author: User?, text: String?, score: Long?) {
        this.id = id
        this.job = job
        this.author = author
        this.text = text
        this.score = score
    }

    override fun toString(): String {
        return "Review(id=$id, job=$job, author=$author, text=$text, score=$score)"
    }
}