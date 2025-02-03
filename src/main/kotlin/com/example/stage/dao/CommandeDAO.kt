package com.example.stage.dao

import com.example.stage.model.entity.Commande
import org.springframework.data.jpa.repository.JpaRepository

interface CommandeDAO : JpaRepository<Commande, Long> {
    fun findByUtilisateurId(utilisateurId: Long): List<Commande>
}
