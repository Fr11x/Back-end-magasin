package com.example.stage.service

import com.example.stage.dao.PanierDAO
import com.example.stage.dao.InventaireDAO
import com.example.stage.dao.PanierArticleDAO
import com.example.stage.model.entity.Panier
import com.example.stage.model.entity.PanierArticle
import com.example.stage.model.entity.Inventaire
import com.example.stage.dao.UtilisateurDAO
import org.springframework.stereotype.Service

@Service
class PanierService(
    private val panierDAO: PanierDAO,
    private val panierArticleDAO: PanierArticleDAO,
    private val inventaireDAO: InventaireDAO,
    private val utilisateurDAO: UtilisateurDAO
) {
    fun creerPanier(utilisateurId: Long): Panier {
        val utilisateur = utilisateurDAO.findById(utilisateurId).orElseThrow {
            IllegalArgumentException("Utilisateur non trouvé")
        }

        // Si l'utilisateur a déjà un panier, on le retourne
        val panierExistant = panierDAO.findByUtilisateurId(utilisateurId)
        if (panierExistant != null) {
            return panierExistant
        }

        // Sinon, on crée un nouveau panier pour l'utilisateur
        val nouveauPanier = Panier(utilisateur = utilisateur)
        return panierDAO.save(nouveauPanier)
    }



    fun ajouterArticleAuPanier(utilisateurId: Long, inventaireId: Long, quantite: Int): Panier {
        // Recherche du panier de l'utilisateur
        val panier = panierDAO.findByUtilisateurId(utilisateurId)
            ?: Panier(utilisateur = utilisateurDAO.findById(utilisateurId).orElseThrow())

        // Recherche de l'inventaire pour l'article à ajouter
        val inventaire = inventaireDAO.findById(inventaireId).orElseThrow()

        // Recherche de l'article dans le panier
        val articleExistant = panierArticleDAO.findByPanierIdAndInventaireId(panier.id, inventaireId)

        if (articleExistant != null) {
            // Si l'article existe déjà, on met à jour sa quantité et son prix total
            articleExistant.quantite += quantite
            articleExistant.prixTotal = articleExistant.quantite * inventaire.puHt!!
            panierArticleDAO.save(articleExistant)
        } else {
            // Si l'article n'existe pas encore, on l'ajoute au panier
            val panierArticle = PanierArticle(
                panier = panier,
                inventaire = inventaire,
                quantite = quantite,
                prixTotal = quantite * inventaire.puHt!!
            )
            panierArticleDAO.save(panierArticle)
        }

        // Recalculer le prix total du panier après ajout de l'article
        val totalPanier = panierArticleDAO.findByPanierId(panier.id)
            .sumOf { it.prixTotal }  // Somme des prix des articles du panier

        panier.prixTotal = totalPanier  // Mise à jour du prix total du panier
        panierDAO.save(panier)  // Sauvegarde du panier avec le prix mis à jour

        return panier
    }

    fun retirerArticleDuPanier(utilisateurId: Long, panierArticleId: Long) {
        // Recherche de l'article à supprimer
        val panierArticle = panierArticleDAO.findById(panierArticleId).orElseThrow()

        // Récupération du panier
        val panier = panierArticle.panier

        // Suppression de l'article du panier
        panierArticleDAO.delete(panierArticle)

        // Recalculer le prix total du panier après suppression de l'article
        val totalPanier = panierArticleDAO.findByPanierId(panier.id)
            .sumOf { it.prixTotal }  // Somme des prix des articles restants

        panier.prixTotal = totalPanier  // Mise à jour du prix total
        panierDAO.save(panier)  // Sauvegarde du panier avec le prix mis à jour
    }


    fun getPanierByUtilisateurId(utilisateurId: Long): Panier {
        return panierDAO.findByUtilisateurId(utilisateurId)
            ?: throw IllegalArgumentException("Aucun panier trouvé pour cet utilisateur.")
    }
}
