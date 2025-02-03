package com.example.stage.dao
import com.example.stage.model.entity.Inventaire
import org.springframework.data.jpa.repository.JpaRepository

interface InventaireDAO : JpaRepository<Inventaire, Long>{
    fun findByIdDart(idDart: Int): List<Inventaire>
}