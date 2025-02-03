package com.example.stage.service

import com.example.stage.dao.PanierArticleDAO
import com.example.stage.dao.CommandeDAO
import com.example.stage.dao.CommandeArticleDAO
import com.example.stage.model.entity.Commande
import com.example.stage.model.entity.CommandeArticle
import com.example.stage.model.StatutCommande
import org.springframework.stereotype.Service

@Service
class CommandeService(
    private val panierArticleDAO: PanierArticleDAO,
    private val commandeDAO: CommandeDAO,
    private val commandeArticleDAO: CommandeArticleDAO,
    private val panierService: PanierService
) {

    fun creerCommande(utilisateurId: Long): Commande {
        // Récupérer le panier de l'utilisateur
        val panier = panierService.getPanierByUtilisateurId(utilisateurId)

        // Créer une commande
        val commande = Commande(utilisateur = panier.utilisateur, statut = StatutCommande.EN_COURS)
        commandeDAO.save(commande)

        // Ajouter les articles du panier à la commande
        panier.panierArticles.forEach {
            val commandeArticle = CommandeArticle(
                commande = commande,
                inventaire = it.inventaire,
                quantite = it.quantite,
                prixTotal = it.prixTotal
            )
            commandeArticleDAO.save(commandeArticle)
        }

        // Mettre à jour le statut de la commande
        commande.statut = StatutCommande.TERMINE
        commandeDAO.save(commande)

        // Supprimer le panier une fois la commande passée
        panierArticleDAO.deleteAll(panier.panierArticles)

        return commande
    }
}
