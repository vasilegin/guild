package com.guild.kotlin.adventurer.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.Type
import org.springframework.security.core.GrantedAuthority
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "users", schema = "public")
open class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "Login", unique=true)
    open var login: String? = null

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "Password")
    open var password: String? = null

    @Column(name = "Birthday")
    open var birthday: LocalDate? = null

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "Gender")
    open var gender: String? = null

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "Surname")
    open var surname: String? = null

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "Firstname")
    open var firstname: String? = null

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "Email")
    open var email: String? = null

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "Status")
    open var status: String? = null


    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "Phone_number")
    open var phoneNumber: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "users")
    @JoinColumn(name = "Adventurer_id")
    open var adventurer: Adventurer? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "users")
    @JoinColumn(name = "Guild_staff_id")
    open var guildStaff: GuildStaff? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "users")
    @JoinColumn(name = "Role_id")
    open var role: Role? = null

    @Column(name = "Balance")
    open var balance: BigDecimal? = null

    @OneToMany(mappedBy = "author")
    open var reviews: MutableSet<Review> = mutableSetOf()

    @OneToMany(mappedBy = "adventurer")
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "customer", "adventurer", "group", "reviews", "reports", "photos")
    open var jobs: MutableSet<Job> = mutableSetOf()

    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "customer", "adventurer", "group", "reviews", "reports", "photos")
    open var orders: MutableSet<Job> = mutableSetOf()

    @OneToMany(mappedBy = "adventurer")
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "group", "adventurer")
    open var participates: MutableSet<Participate> = mutableSetOf()

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("hibernateLazyInitializer", "handler", "job", "report", "review", "group", "user")
    open var photos: MutableSet<Photo> = mutableSetOf()


    constructor(
        id: Long?,
        login: String?,
        password: String,
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