package com.example.stage.model.entity

import jakarta.persistence.*
import com.example.stage.model.StatutCommande

@Entity
@Table(name = "commande")
data class Commande(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    val utilisateur: Utilisateur,

    @Enumerated(EnumType.STRING)  // Utilisation de EnumType.STRING pour stocker le statut comme chaîne
    @Column(name = "statut")
    var statut: StatutCommande  // Le statut de la commande est un StatutCommande
)
