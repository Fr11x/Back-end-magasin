package com.example.stage.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "commande_article")
data class CommandeArticle(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "commande_id")
    val commande: Commande, // La commande à laquelle cet article appartient

    @ManyToOne
    @JoinColumn(name = "inventaire_id")
    val inventaire: Inventaire, // Le produit de la commande

    @Column(name = "quantite")
    val quantite: Int, // Quantité de cet article dans la commande

    @Column(name = "prix_total")
    val prixTotal: Double // Calculé comme (quantité * prix unitaire)
)
