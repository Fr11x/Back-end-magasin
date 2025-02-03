package com.example.stage.dao
import com.example.stage.model.entity.Utilisateur
import org.springframework.data.jpa.repository.JpaRepository

interface UtilisateurDAO : JpaRepository<Utilisateur, Long>{
    fun findByAdresse(adresse: String): Utilisateur?
}