package io.github.jakriz.movietrends.service.rotten

import io.github.jakriz.movietrends.model.MovieInfo
import io.github.jakriz.movietrends.model.Role
import io.github.jakriz.movietrends.service.SiteProcessor
import io.github.jakriz.movietrends.service.rotten.util.ratingFromPercentageText
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.springframework.stereotype.Service

@Service
class RottenProcessor : SiteProcessor {

    override fun getSiteName(): String = "rottenTomatoes"

    override fun urlForName(name: String) = "http://www.rottentomatoes.com/celebrity/$name"

    override fun parseToMovieInfos(html: String): List<MovieInfo> {
        val elements = Jsoup.parse(html).select("#filmography_box #filmographyTbl")[0].select("tr")
        val elementsList = elements.toList()

        return elementsList
                .drop(1)
                .map {
                    val tds = it.select("td")
                    val rating = ratingFromPercentageText(tds[0]?.text())
                    val title = tds[1]?.text()
                    val roles = rolesFromHtml(tds[2])
                    val year = tds[4]?.text()?.toInt()
                    if (rating != null && title != null && year != null) {
                        return@map MovieInfo(title, rating, roles, year)
                    } else {
                        return@map null
                    }
                }
                .filterNotNull()
                .filter { it.roles.isNotEmpty() }
    }

    private fun rolesFromHtml(element: Element?): Set<Role> {
        if (element != null) {
            return element.select("li > em")
                    .map {
                        when (it.text()) {
                            "Director" -> Role.DIRECTOR
                            "Producer", "Executive Producer" -> Role.PRODUCER
                            "Actor" -> Role.ACTOR
                            "Screenwriter" -> Role.WRITER
                            else -> null
                        }
                    }
                    .filterNotNull()
                    .toSet()
        } else {
            return emptySet()
        }
    }

}