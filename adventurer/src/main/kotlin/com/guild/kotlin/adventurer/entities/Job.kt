package com.guild.kotlin.adventurer.entities

import org.hibernate.annotations.Type
import java.math.BigDecimal
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "jobs", schema = "public")
open class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Customer_id")
    open var customer: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Adventurer_id")
    open var adventurer: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Group_id")
    open var group: Group? = null

    @Lob
    @Column(name = "Title")
    open var title: String? = null

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "Description")
    open var description: String? = null

    @Lob
    @Column(name = "Rank")
    open var rank: String? = null

    @Column(name = "Reward")
    open var reward: BigDecimal? = null

    @Lob
    @Column(name = "Location")
    open var location: String? = null

    @Lob
    @Column(name = "Status")
    open var status: String? = null

    @Column(name = "Date_created")
    open var dateCreated: OffsetDateTime? = null

    @Column(name = "Date_posted")
    open var datePosted: OffsetDateTime? = null

    @Column(name = "Date_accepted")
    open var dateAccepted: OffsetDateTime? = null

    @Column(name = "Date_resolved")
    open var dateResolved: OffsetDateTime? = null

    @OneToMany(mappedBy = "job")
    open var reviews: MutableSet<Review> = mutableSetOf()

    @OneToMany(mappedBy = "job")
    open var reports: MutableSet<Report> = mutableSetOf()

    @OneToMany(mappedBy = "job")
    open var photos: MutableSet<Photo> = mutableSetOf()

    constructor(
        id: Long?,
        customer: User?,
        adventurer: User?,
        group: Group?,
        title: String?,
        description: String?,
        rank: String?,
        reward: BigDecimal?,
        location: String?,
        status: String?,
        dateCreated: OffsetDateTime?,
        datePosted: OffsetDateTime?,
        dateAccepted: OffsetDateTime?,
        dateResolved: OffsetDateTime?
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
        this.dateCreated = dateCreated
        this.datePosted = datePosted
        this.dateAccepted = dateAccepted
        this.dateResolved = dateResolved
    }

    override fun toString(): String {
        return "Job(id=$id, customer=$customer, adventurer=$adventurer, group=$group, title=$title, description=$description, rank=$rank, reward=$reward, location=$location, status=$status, dateCreated=$dateCreated, datePosted=$datePosted, dateAccepted=$dateAccepted, dateResolved=$dateResolved)"
    }


}