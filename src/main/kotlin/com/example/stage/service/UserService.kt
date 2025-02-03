package com.example.stage.service

import com.example.stage.model.entity.Utilisateur
import com.example.stage.config.SecurityConfig
import com.example.stage.dao.UtilisateurDAO
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val utilisateurDAO: UtilisateurDAO,
    private val passwordEncoder: PasswordEncoder
) {

    fun register(user: Utilisateur): Utilisateur {
        // Vérifie si l'utilisateur existe déjà
        if (utilisateurDAO.findByAdresse(user.adresse!!) != null) {
            throw IllegalArgumentException("Un utilisateur avec cette adresse existe déjà.")
        }

        // Chiffre le mot de passe et sauvegarde l'utilisateur
        user.mdp = passwordEncoder.encode(user.mdp)
        return utilisateurDAO.save(user)
    }

    fun login(adresse: String, password: String): Utilisateur {
        val user = utilisateurDAO.findByAdresse(adresse)
            ?: throw IllegalArgumentException("Adresse e-mail ou mot de passe incorrect")

        // Vérifie si le mot de passe correspond
        if (!passwordEncoder.matches(password, user.mdp)) {
            throw IllegalArgumentException("Adresse e-mail ou mot de passe incorrect")
        }

        return user
    }
}
