package com.example.stage.model.entity
import jakarta.persistence.*

@Entity
@Table(name = "inventaire")

data class Inventaire(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "id_dart", nullable = false)
    val idDart: Int,

    @Column(name = "designation", nullable = false)
    val designation: String,

    @Column(name = "stock")
    val stock: Int?,

    @Column(name = "pu_ht")
    val puHt: Double?
)
