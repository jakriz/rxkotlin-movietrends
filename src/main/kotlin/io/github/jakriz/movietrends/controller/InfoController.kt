package io.github.jakriz.movietrends.controller

import io.github.jakriz.movietrends.controller.exception.NotFoundException
import io.github.jakriz.movietrends.model.MovieInfo
import io.github.jakriz.movietrends.model.SiteInfo
import io.github.jakriz.movietrends.service.InfoComposer
import io.github.jakriz.movietrends.service.SiteProcessor
import io.github.jakriz.movietrends.service.rotten.RottenProcessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class InfoController @Autowired constructor(val infoComposer: InfoComposer,
                                            val siteProcessors: List<SiteProcessor>) {

    @RequestMapping("/{name}")
    @ResponseBody
    fun getInfo(@PathVariable name: String) : List<SiteInfo> {
        return siteProcessors.map {
            return@map infoComposer.getAndCompose(it, name)
        }
    }
}