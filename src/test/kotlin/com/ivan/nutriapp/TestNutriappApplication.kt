package com.ivan.nutriapp

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<NutriappApplication>().with(TestcontainersConfiguration::class).run(*args)
}
