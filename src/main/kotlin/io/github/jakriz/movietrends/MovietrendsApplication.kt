package io.github.jakriz.movietrends

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class MovietrendsApplication

fun main(args: Array<String>) {
    SpringApplication.run(MovietrendsApplication::class.java, *args)
}
