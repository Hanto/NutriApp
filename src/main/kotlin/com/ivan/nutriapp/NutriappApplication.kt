package com.ivan.nutriapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NutriappApplication

fun main(args: Array<String>) {
	runApplication<NutriappApplication>(*args)
}
