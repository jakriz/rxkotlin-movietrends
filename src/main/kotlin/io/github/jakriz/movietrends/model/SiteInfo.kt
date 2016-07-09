package io.github.jakriz.movietrends.model

class SiteInfo(val name: String,
               val movies: List<MovieInfo>) {
    var trend: TrendInfo? = null
}