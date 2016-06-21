package io.github.jakriz.movietrends.service

import io.github.jakriz.movietrends.model.MovieInfo

interface SiteProcessor {

    fun getSiteName(): String

    fun urlForName(name: String): String

    fun parseToMovieInfos(html: String): List<MovieInfo>
}