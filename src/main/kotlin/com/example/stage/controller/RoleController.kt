package com.example.stage.controller

import com.example.stage.model.entity.Role
import com.example.stage.service.RoleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/roles")
class RoleController(private val roleService: RoleService) {

    // Ajouter un rôle
    @PostMapping("/creer")
    fun creerRole(@RequestParam nom: String): ResponseEntity<Role> {
        return try {
            val role = roleService.creerRole(nom)
            ResponseEntity.ok(role)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(null)
        }
    }

    // Récupérer tous les rôles
    @GetMapping("/")
    fun getAllRoles(): ResponseEntity<List<Role>> {
        return ResponseEntity.ok(roleService.getAllRoles())
    }
}
