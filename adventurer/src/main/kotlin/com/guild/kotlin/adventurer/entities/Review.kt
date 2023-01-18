package com.guild.kotlin.adventurer.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.Type
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "reviews")
open class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "customer", "adventurer", "group", "reviews", "reports", "photos")
    @JoinColumn(name = "Job_id", insertable = false, updatable = false)
    open var job: Job? = null

    @Column(name = "Job_id", nullable = false)
    open var jobId: Long? = null


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "adventurer", "guildStaff", "jobs", "orders", "reviews", "participates", "photos")
    @JoinColumn(name = "Author_id", insertable = false, updatable = false)
    open var author: User? = null

    @Column(name = "Author_id", nullable = false)
    open var authorId: Long? = null

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "Text", nullable = false)
    open var text: String? = null

    @Column(name = "Score")
    open var score: Long? = null

    @Column(name = "Date_created")
    open var dateCreated: OffsetDateTime? = null

    @OneToMany(mappedBy = "review")
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "job", "report", "review", "group", "user")
    open var photos: MutableSet<Photo> = mutableSetOf()

    constructor(
        id: Long?,
//        job: Job?,
//        author: User?,
        text: String?,
        score: Long?,
        jobId: Long?,
        authorId: Long?) {
        this.id = id
//        this.job = job
        this.jobId = jobId
//        this.author = author
        this.authorId = authorId
        this.text = text
        this.score = score
        this.dateCreated = dateCreated
    }
    constructor() {
    }

    override fun toString(): String {
        return "Review(id=$id, text=$text, score=$score)"
    }
}