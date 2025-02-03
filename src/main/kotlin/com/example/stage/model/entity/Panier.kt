package com.example.stage.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "panier")
data class Panier(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    val utilisateur: Utilisateur,

    @OneToMany(mappedBy = "panier", cascade = [CascadeType.ALL], orphanRemoval = true)
    val panierArticles: List<PanierArticle> = mutableListOf(),

    @Column(name = "prix_total")
    var prixTotal: Double = 0.0  // Prix total du panier
)
