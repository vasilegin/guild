package com.guild.kotlin.adventurer.entity

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.sql.Timestamp

@Entity
@Table(name = "jobs")
class Jobs {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "Customer_id")
    var customer: Users? = null

    @Column(name = "Adventurer_id")
    var adventurer: Users? = null

    @Column(name = "Group_id")
    var group: Groups? = null

    @Column(name = "Title")
    var title: String? = null

    @Column(name = "Description")
    var description: String? = null

    @Column(name = "Rank")
    var rank: String? = null

    @Column(name = "Reward")
    var reward: Float? = null

    @Column(name = "Location")
    var location: String? = null

    @Column(name = "Status")
    var status: String? = null

    @Column(name = "Date_created")
    var date_created: Timestamp? = null

    @Column(name = "Date_posted")
    var date_posted: Timestamp? = null

    @Column(name = "Date_accepted")
    var date_accepted: Timestamp? = null

    @Column(name = "Date_resolved")
    var date_resolved: Timestamp? = null

    constructor(
        id: Long,
        customer: Users?,
        adventurer: Users?,
        group: Groups?,
        title: String?,
        description: String?,
        rank: String?,
        reward: Float?,
        location: String?,
        status: String?,
        date_created: Timestamp?,
        date_posted: Timestamp?,
        date_accepted: Timestamp?,
        date_resolved: Timestamp?
    ) {
        this.id = id
        this.customer = customer
        this.adventurer = adventurer
        this.group = group
        this.title = title
        this.description = description
        this.rank = rank
        this.reward = reward
        this.location = location
        this.status = status
        this.date_created = date_created
        this.date_posted = date_posted
        this.date_accepted = date_accepted
        this.date_resolved = date_resolved
    }

    override fun toString(): String {
        return "Jobs(id=$id, customer=$customer, adventurer=$adventurer, group=$group, title=$title, description=$description, rank=$rank, reward=$reward, location=$location, status=$status, date_created=$date_created, date_posted=$date_posted, date_accepted=$date_accepted, date_resolved=$date_resolved)"
    }
}