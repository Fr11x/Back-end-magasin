package com.example.stage.controller
import com.example.stage.dao.UtilisateurDAO
import com.example.stage.model.entity.Role
import com.example.stage.model.entity.Utilisateur
import org.springframework.web.bind.annotation.*
import com.example.stage.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@CrossOrigin(origins = ["http://localhost:4200"])  // Permet les requêtes venant de http://localhost:4200
@RestController
@RequestMapping("/api/utilisateur")
class UtilisateurController(
    private val userService: UserService,
    private val utilisateurDAO: UtilisateurDAO
) {

    @PostMapping("/{utilisateurId}/changer-role")
    fun changerRoleUtilisateur(
        @PathVariable utilisateurId: Long,
        @RequestParam roleNom: String
    ): ResponseEntity<Utilisateur> {
        return try {
            val utilisateur = userService.changerRoleUtilisateur(utilisateurId, roleNom)
            ResponseEntity.ok(utilisateur)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(null)
        }
    }
    @GetMapping("/")
    fun getAllUtilisateur(): ResponseEntity<List<Utilisateur>> {
        val utilisateur = utilisateurDAO.findAll()
        return ResponseEntity(utilisateur, HttpStatus.OK)
    }
}




