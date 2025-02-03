package com.example.stage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StageApplication

fun main(args: Array<String>) {
    runApplication<StageApplication>(*args)
}