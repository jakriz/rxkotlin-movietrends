package io.github.jakriz.movietrends.service

import io.github.jakriz.movietrends.service.client.HttpClient
import io.github.jakriz.movietrends.model.MovieInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InfoComposerImpl @Autowired constructor(val httpClient: HttpClient) : InfoComposer {

    override fun getAndCompose(siteProcessor: SiteProcessor, name: String): List<MovieInfo>? {
        val html = httpClient.get(siteProcessor.urlForName(name))

        if (html != null && html.isNotBlank()) {
            return siteProcessor.parseToMovieInfos(html).sortedBy { it.year }
        } else {
            return null
        }
    }

}