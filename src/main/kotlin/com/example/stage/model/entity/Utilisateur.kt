package com.example.stage.model.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "utilisateur")
data class Utilisateur(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "nom")
    @field:NotBlank(message = "Le nom est obligatoire")
    var nom: String? = null,

    @Column(name = "prenom")
    @field:NotBlank(message = "Le prénom est obligatoire")
    var prenom: String? = null,

    @Column(name = "adresse_mail", unique = true)
    @field:Email(message = "Adresse mail invalide")
    @field:NotBlank(message = "L'adresse mail est obligatoire")
    var adresse: String? = null,

    @Column(name = "mot_de_passe")
    @field:NotBlank(message = "Le mot de passe est obligatoire")
    var mdp: String? = null,

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference
    @JsonProperty("roles") // Cela garantit que la liste des rôles est incluse dans la réponse JSON
    var role: Role?
)
