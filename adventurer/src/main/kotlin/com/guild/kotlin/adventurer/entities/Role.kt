package com.guild.kotlin.adventurer.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.Type
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role", schema = "public")
open class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	open var id: Long? = null

	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "Name")
	open var name: String? = null

	@OneToMany(mappedBy = "role")
	@JsonIgnoreProperties("hibernateLazyInitializer", "handler", "adventurer", "guildStaff", "jobs", "orders", "reviews", "participates", "photos")
	open var users: MutableSet<User> = mutableSetOf()

	constructor(id: Long?, name: String?) {
		this.id = id
		this.name = name
	}

	override fun toString(): String {
		return "Role(id=$id, name=$name)"
	}
}