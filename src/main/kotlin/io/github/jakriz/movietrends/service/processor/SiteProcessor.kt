package io.github.jakriz.movietrends.service.processor

import io.github.jakriz.movietrends.model.MovieInfo
import io.github.jakriz.movietrends.model.Role

interface SiteProcessor {

    fun getSiteName(): String

    fun urlForName(name: String): String

    fun parseToMovieInfos(html: String, allowedRoles: Set<Role>?): List<MovieInfo>
}