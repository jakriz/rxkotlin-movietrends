package io.github.jakriz.movietrends.service

import io.github.jakriz.movietrends.service.client.HttpClient
import io.github.jakriz.movietrends.model.MovieInfo
import io.github.jakriz.movietrends.model.SiteInfo
import io.github.jakriz.movietrends.service.processor.SiteProcessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import rx.Observable

@Service
class InfoComposerImpl @Autowired constructor(val httpClient: HttpClient) : InfoComposer {

    override fun getAndCompose(siteProcessor: SiteProcessor, name: String): Observable<SiteInfo> {
        return httpClient.get(siteProcessor.urlForName(name))
                .map { html ->
                    siteProcessor.parseToMovieInfos(html).sortedBy { it.year }
                }
                .map {
                    SiteInfo(siteProcessor.getSiteName(), it)
                }
    }

}