package com.guild.kotlin.adventurer.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "photos", schema = "public")
open class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "Location")
    open var location: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "customer", "adventurer", "group", "reviews", "reports", "photos")
    @JoinColumn(name = "Job_id", insertable = false, updatable = false)
    open var job: Job? = null

    @Column(name = "Job_id")
    open var jobId: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("hibernateLazyInitializer", "job", "photos")
    @JoinColumn(name = "Report_id")
    open var report: Report? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("hibernateLazyInitializer", "job", "author", "photos")
    @JoinColumn(name = "Review_id")
    open var review: Review? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "jobs", "participates", "photos")
    @JoinColumn(name = "Group_id")
    open var group: Group? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "adventurer", "guildStaff", "jobs", "orders", "reviews", "participates", "photos")
    @JoinColumn(name = "User_id")
    open var user: User? = null

    constructor(id: Long?, location: String?, job: Job?, report: Report?, review: Review?, group: Group?, user: User?) {
        this.id = id
        this.location = location
        this.job = job
        this.report = report
        this.review = review
        this.group = group
        this.user = user
    }

    constructor(id: Long?, location: String?, jobId: Long?) {
        this.id = id
        this.location = location
        this.jobId = jobId
    }

    override fun toString(): String {
        return "Photo(id=$id, location=$location, job=$job, report=$report, review=$review, group=$group, user=$user)"
    }
}