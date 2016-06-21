package io.github.jakriz.movietrends.model

data class MovieInfo(val title: String,
                     val rating: Double,
                     val roles: Set<Role>,
                     val year: Int)
