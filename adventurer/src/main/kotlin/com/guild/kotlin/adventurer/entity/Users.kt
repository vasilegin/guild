package com.guild.kotlin.adventurer.entity

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.sql.Date

@Entity
@Table(name = "users")
class Users {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "Login")
    var login: String? = null

    @Column(name = "Password")
    var password: String? = null

    @Column(name = "Birthday")
    var birthday: Date? = null

    @Column(name = "Gender")
    var gender: String? = null

    @Column(name = "Surname")
    var surname: String? = null

    @Column(name = "Firstname")
    var firstname: String? = null

    @Column(name = "Email")
    var email: String? = null

    @Column(name = "Phone_number")
    var phone_number: String? = null

    @Column(name = "Adventurer_id")
    var adventurer: Adventurer? = null

    @Column(name = "Guild_staff_id")
    var guild_staff: Guild_staff? = null

    @Column(name = "Balance")
    var balance: Float? = null

    constructor(
        id: Long,
        login: String?,
        password: String?,
        birthday: Date?,
        gender: String?,
        surname: String?,
        firstname: String?,
        email: String?,
        phone_number: String?,
        adventurer: Adventurer?,
        guild_staff: Guild_staff?,
        balance: Float?
    ) {
        this.id = id
        this.login = login
        this.password = password
        this.birthday = birthday
        this.gender = gender
        this.surname = surname
        this.firstname = firstname
        this.email = email
        this.phone_number = phone_number
        this.adventurer = adventurer
        this.guild_staff = guild_staff
        this.balance = balance
    }

    override fun toString(): String {
        return "Users(id=$id, login=$login, password=$password, birthday=$birthday, gender=$gender, surname=$surname, firstname=$firstname, email=$email, phone_number=$phone_number, adventurer=$adventurer, guild_staff=$guild_staff, balance=$balance)"
    }
}