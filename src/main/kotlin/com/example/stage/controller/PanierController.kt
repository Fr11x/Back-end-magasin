package com.example.stage.controller

import com.example.stage.model.entity.ArticlePanierRequest
import com.example.stage.model.entity.Panier
import com.example.stage.model.entity.PanierArticle
import com.example.stage.service.PanierService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/panier")
class PanierController(
    private val panierService: PanierService
) {

    // Endpoint pour créer un panier pour un utilisateur
    @PostMapping("/creer/{utilisateurId}")
    fun creerPanier(@PathVariable utilisateurId: Long): ResponseEntity<Panier> {
        return try {
            val panier = panierService.creerPanier(utilisateurId)
            println("test")
            ResponseEntity.ok(panier)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(null)
        }
    }

    // Endpoint pour ajouter un article dans le panier
    @PostMapping("/ajouter")
    fun ajouterArticleAuPanier(@RequestBody articlePanier: ArticlePanierRequest): ResponseEntity<Panier> {
        // Extraction des informations de l'objet reçu (par exemple articlePanier)
        val utilisateurId = articlePanier.utilisateurId
        val inventaireId = articlePanier.inventaireId
        val quantite = articlePanier.quantite

        // Appel de la méthode du service avec les arguments nécessaires
        return try {
            val panier = panierService.ajouterArticleAuPanier(utilisateurId, inventaireId, quantite)
            ResponseEntity.ok(panier)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(null)
        }
    }

    // Endpoint pour retirer un article du panier
    @DeleteMapping("/retirer/{panierId}/{articleId}")
    fun retirerArticleDuPanier(@PathVariable panierId: Long, @PathVariable articleId: Long): ResponseEntity<String> {
        return try {
            panierService.retirerArticleDuPanier(panierId, articleId)
            ResponseEntity.ok("Article retiré du panier")
        } catch (e: Exception) {
            ResponseEntity.badRequest().body("Erreur lors du retrait de l'article")
        }
    }

    // Endpoint pour récupérer le panier d'un utilisateur
    @GetMapping("/utilisateur/{utilisateurId}")
    fun getPanierByUtilisateur(@PathVariable utilisateurId: Long): ResponseEntity<Panier> {
        val panier = panierService.getPanierByUtilisateurId(utilisateurId)
        return if (panier != null) {
            ResponseEntity.ok(panier)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
