package com.example.stage.controller

import com.example.stage.dao.InventaireDAO
import com.example.stage.model.entity.Inventaire
import com.example.stage.service.InventaireService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.apache.poi.ss.usermodel.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@CrossOrigin(origins = ["http://localhost:4200"])
@RestController
@RequestMapping("/inventaire")
class InventaireController(
    private val inventaireDAO: InventaireDAO,  // Injection du DAO directement
    private val inventaireService: InventaireService
) {

    // Endpoint pour importer un fichier Excel
    @PostMapping("/import")
    fun importFromExcel(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        // Sauvegarder le fichier temporairement sur le serveur
        val targetLocation = Paths.get("uploads", file.originalFilename ?: "file.xlsx")
        Files.createDirectories(targetLocation.parent) // Créer le répertoire si nécessaire
        file.inputStream.use { inputStream ->
            Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING)
        }

        // Appel du service pour importer les données du fichier Excel
        return try {
            inventaireService.importFromExcel(targetLocation.toString())
            ResponseEntity("Fichier importé avec succès.", HttpStatus.OK)
        } catch (e: Exception) {
            // En cas d'erreur d'import
            ResponseEntity("Erreur lors de l'importation du fichier : ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    // Endpoint pour récupérer la liste des inventaire
    @GetMapping("/")
    fun getAllinventaire(): ResponseEntity<List<Inventaire>> {
        val inventaire = inventaireDAO.findAll() // Utilisation du DAO directement
        return ResponseEntity(inventaire, HttpStatus.OK)
    }

    // Endpoint pour récupérer un inventaire spécifique par ID
    @GetMapping("/{id}")
    fun getInventaireById(@PathVariable id: Long): ResponseEntity<Inventaire> {
        val inventaire = inventaireDAO.findById(id).orElse(null) // Utilisation du DAO directement
        return if (inventaire != null) {
            ResponseEntity(inventaire, HttpStatus.OK)
        } else {
            ResponseEntity(inventaire, HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/idDart/{idDart}")
    fun getInventaireByIdDart(@PathVariable idDart: Int): ResponseEntity<List<Inventaire>> {
        // Recherche dans la base de données par idDart
        val inventaire = inventaireDAO.findByIdDart(idDart)
        return if (inventaire != null) {
            ResponseEntity(inventaire, HttpStatus.OK)
        } else {
            ResponseEntity(inventaire, HttpStatus.NOT_FOUND)
        }// Utilisation de la méthode personnalisée dans le DAO
}}
