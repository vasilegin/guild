package com.guild.kotlin.adventurer.entities

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "users", schema = "public")
open class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Lob
    @Column(name = "Login")
    open var login: String? = null

    @Lob
    @Column(name = "Password")
    open var password: String? = null

    @Column(name = "Birthday")
    open var birthday: LocalDate? = null

    @Lob
    @Column(name = "Gender")
    open var gender: String? = null

    @Lob
    @Column(name = "Surname")
    open var surname: String? = null

    @Lob
    @Column(name = "Firstname")
    open var firstname: String? = null

    @Lob
    @Column(name = "Email")
    open var email: String? = null

    @Lob
    @Column(name = "Phone_number")
    open var phoneNumber: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Adventurer_id")
    open var adventurer: Adventurer? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Guild_staff_id")
    open var guildStaff: GuildStaff? = null

    @Column(name = "Balance")
    open var balance: BigDecimal? = null

    @OneToMany(mappedBy = "author")
    open var reviews: MutableSet<Review> = mutableSetOf()

    @OneToMany(mappedBy = "adventurer")
    open var jobs: MutableSet<Job> = mutableSetOf()

    @OneToMany(mappedBy = "customer")
    open var orders: MutableSet<Job> = mutableSetOf()

    @OneToMany(mappedBy = "adventurer")
    open var participates: MutableSet<Participate> = mutableSetOf()

    @OneToMany(mappedBy = "user")
    open var photos: MutableSet<Photo> = mutableSetOf()

    constructor(
        id: Long?,
        login: String?,
        password: String?,
        birthday: LocalDate?,
        gender: String?,
        surname: String?,
        firstname: String?,
        email: String?,
        phoneNumber: String?,
        adventurer: Adventurer?,
        guildStaff: GuildStaff?,
        balance: BigDecimal?
    ) {
        this.id = id
        this.login = login
        this.password = password
        this.birthday = birthday
        this.gender = gender
        this.surname = surname
        this.firstname = firstname
        this.email = email
        this.phoneNumber = phoneNumber
        this.adventurer = adventurer
        this.guildStaff = guildStaff
        this.balance = balance
    }

    override fun toString(): String {
        return "User(id=$id, login=$login, password=$password, birthday=$birthday, gender=$gender, surname=$surname, firstname=$firstname, email=$email, phoneNumber=$phoneNumber, adventurer=$adventurer, guildStaff=$guildStaff, balance=$balance)"
    }
}