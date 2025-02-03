package com.example.stage.model.entity
import jakarta.persistence.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan

@EntityScan("com.example.stage.model.entity")
@Entity
@Table(name = "role")

data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,


)
