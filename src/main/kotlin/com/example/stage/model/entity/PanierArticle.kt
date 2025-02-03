package com.example.stage.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "panier_article")
data class PanierArticle(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "panier_id")
    val panier: Panier, // Le panier auquel cet article appartient

    @ManyToOne
    @JoinColumn(name = "inventaire_id")
    val inventaire: Inventaire, // Le produit ajouté au panier

    @Column(name = "quantite")
    var quantite: Int, // Quantité de cet article dans le panier

    @Column(name = "prix_total")
    var prixTotal: Double // Calculé comme (quantité * prix unitaire)
)
