package com.guild.kotlin.adventurer.entities

import javax.persistence.*

@Entity
@Table(name = "photos", schema = "public")
open class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Lob
    @Column(name = "Location")
    open var location: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Job_id")
    open var job: Job? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Report_id")
    open var report: Report? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Review_id")
    open var review: Review? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Group_id")
    open var group: Group? = null

    @ManyToOne(fetch = FetchType.LAZY)
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

    override fun toString(): String {
        return "Photo(id=$id, location=$location, job=$job, report=$report, review=$review, group=$group, user=$user)"
    }
}