package com.guild.kotlin.adventurer.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.Type
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "reports", schema = "public")
open class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "customer", "adventurer", "group", "reviews", "reports", "photos")
    @JoinColumn(name = "Job_id", insertable = false, updatable = false)
    open var job: Job? = null

    @Column(name = "Job_id")
    open var jobId: Long? = null

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "Text")
    open var text: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "adventurer", "guildStaff", "jobs", "orders", "reviews", "participates", "photos")
    @JoinColumn(name = "Author_id", insertable = false, updatable = false)
    open var author: User? = null

    @Column(name = "Author_id")
    open var authorId: Long? = null

    @Column(name = "Date_created")
    open var dateCreated: OffsetDateTime? = null

    @OneToMany(mappedBy = "report")
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "job", "report", "review", "group", "user")
    open var photos: MutableSet<Photo> = mutableSetOf()

    constructor(
        id: Long?,
//        job: Job?,
//        author: User?,
        text: String?,
        dateCreated: OffsetDateTime?,
        jobId: Long?,
        authorId: Long?) {
        this.id = id
//        this.job = job
        this.jobId = jobId
//        this.author = author
        this.authorId = authorId
        this.text = text
        this.dateCreated = dateCreated
    }

    override fun toString(): String {
        return "Report(id=$id, job=$job, text=$text)"
    }
}