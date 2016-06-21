package io.github.jakriz.movietrends.service

import io.github.jakriz.movietrends.model.MovieInfo
import rx.Observable

interface InfoComposer {

    fun getAndCompose(siteProcessor: SiteProcessor, name: String): Observable<List<MovieInfo>>
}