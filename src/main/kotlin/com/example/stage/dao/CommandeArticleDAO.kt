package com.example.stage.dao

import com.example.stage.model.entity.Commande
import com.example.stage.model.entity.CommandeArticle
import org.springframework.data.jpa.repository.JpaRepository

interface CommandeArticleDAO : JpaRepository<CommandeArticle, Long> {
}
