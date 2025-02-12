package com.example.stage.model.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "role")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "nom", unique = true, nullable = false)
    val nom: String,

    @OneToMany(mappedBy = "role", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JsonManagedReference
    val utilisateurs: MutableList<Utilisateur> = mutableListOf()
)