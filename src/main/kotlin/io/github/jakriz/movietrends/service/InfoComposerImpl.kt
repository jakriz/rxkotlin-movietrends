package io.github.jakriz.movietrends.service

import io.github.jakriz.movietrends.model.Role
import io.github.jakriz.movietrends.service.client.HttpClient
import io.github.jakriz.movietrends.model.SiteInfo
import io.github.jakriz.movietrends.service.calc.TrendCalculator
import io.github.jakriz.movietrends.service.processor.SiteProcessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import rx.Observable
import rx.lang.kotlin.filterNotNull

@Service
class InfoComposerImpl @Autowired constructor(val httpClient: HttpClient,
                                              val trendCalculator: TrendCalculator) : InfoComposer {

    override fun getAndCompose(siteProcessor: SiteProcessor, name: String, allowedRoles: Set<Role>?): Observable<SiteInfo> {
        val movieInfoObservable = httpClient.get(siteProcessor.urlForName(name))
                .map { html ->
                    siteProcessor.parseToMovieInfos(html, allowedRoles)
                }
                .filterNotNull()
                .map { movieInfo ->
                    movieInfo.sortedBy { it.year }
                }

        return Observable.zip(
                movieInfoObservable.map {
                    SiteInfo(siteProcessor.getSiteName(), it)
                },
                movieInfoObservable.map { movieInfos ->
                    val ratingsData = movieInfos.map { movie -> Pair(movie.year, movie.rating) }
                    trendCalculator.calculate(ratingsData)
                },
                { siteInfo, trend ->
                    siteInfo.trend = trend
                    siteInfo
                }
        )
    }

}