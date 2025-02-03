package com.example.stage.dao

import com.example.stage.model.entity.Panier
import org.springframework.data.jpa.repository.JpaRepository

interface PanierDAO : JpaRepository<Panier, Long> {
    fun findByUtilisateurId(utilisateurId: Long): Panier?
}
