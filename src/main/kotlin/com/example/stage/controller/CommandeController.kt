package com.example.stage.controller

import com.example.stage.dao.CommandeDAO
import com.example.stage.model.entity.Commande
import com.example.stage.service.CommandeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/commandes")
class CommandeController(
    private val commandeService: CommandeService,
    private val commandeDAO: CommandeDAO
) {

    // Endpoint pour créer une commande à partir du panier
    @PostMapping("/creer/{panierId}")
    fun creerCommande(@PathVariable panierId: Long): ResponseEntity<Commande> {
        return try {
            val commande = commandeService.creerCommande(panierId)
            ResponseEntity.ok(commande)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(null)
        }
    }

    // Endpoint pour récupérer toutes les commandes
    @GetMapping("/")
    fun getAllCommandes(): List<Commande> {
        return commandeDAO.findAll()

    }

    // Endpoint pour récupérer une commande par ID
    @GetMapping("/{id}")
    fun getCommandeById(@PathVariable id: Long): ResponseEntity<Commande> {
        val commande = commandeDAO.findById(id)
        return if (commande != null) {
            ResponseEntity.ok(commande.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
