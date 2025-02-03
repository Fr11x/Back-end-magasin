package com.example.stage.controller

import com.example.stage.model.entity.Utilisateur
import com.example.stage.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userService: UserService
) {

    @PostMapping("/register")
    fun register(@RequestBody user: Utilisateur): ResponseEntity<Utilisateur> {
        return try {
            val registeredUser = userService.register(user)
            ResponseEntity.ok(registeredUser)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(null)
        }
    }

    @PostMapping("/login")
    fun login(@RequestParam adresse: String, @RequestParam password: String): ResponseEntity<Utilisateur> {
        return try {
            val user = userService.login(adresse, password)
            ResponseEntity.ok(user)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(401).body(null)
        }
    }
}
