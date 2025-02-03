package com.example.stage.dao
import com.example.stage.model.entity.Inventaire
import com.example.stage.model.entity.PanierArticle
import org.springframework.data.jpa.repository.JpaRepository

interface PanierArticleDAO : JpaRepository<PanierArticle, Long>{
    fun findByPanierId(panierId: Long): List<PanierArticle>

    fun findByPanierIdAndInventaireId(panierId: Long, inventaireId: Long): PanierArticle?
}