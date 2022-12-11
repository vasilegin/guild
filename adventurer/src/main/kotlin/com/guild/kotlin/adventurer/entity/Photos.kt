package com.guild.kotlin.adventurer.entity

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
@Entity
@Table(name = "photos")
class Photos {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "Location")
    var location: String? = null

    @ManyToOne
    @JoinColumn(name = "Job_id")
    var job: Jobs? = null

    @ManyToOne
    @JoinColumn(name = "Report_id")
    var report: Reports? = null

    @ManyToOne
    @JoinColumn(name = "Review_id")
    var review: Reviews? = null

    @ManyToOne
    @JoinColumn(name = "Group_id")
    var group: Groups? = null

    @ManyToOne
    @JoinColumn(name = "User_id")
    var user: Users? = null

    constructor(
        id: Long,
        location: String?,
        job: Jobs?,
        report: Reports?,
        review: Reviews?,
        group: Groups?,
        user: Users?
    ) {
        this.id = id
        this.location = location
        this.job = job
        this.report = report
        this.review = review
        this.group = group
        this.user = user
    }

    override fun toString(): String {
        return "Photos(id=$id, location=$location, job=$job, report=$report, review=$review, group=$group, user=$user)"
    }
}