package io.github.jakriz.movietrends.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.jakriz.movietrends.model.SiteInfo
import io.github.jakriz.movietrends.service.InfoComposer
import io.github.jakriz.movietrends.service.processor.SiteProcessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.context.request.async.DeferredResult
import rx.Observable
import rx.Observer
import javax.servlet.http.HttpServletResponse

@Controller
class InfoController @Autowired constructor(val infoComposer: InfoComposer,
                                            val siteProcessors: List<SiteProcessor>) {

    @RequestMapping("/{name}")
    @ResponseBody
    fun getInfo(@PathVariable name: String, response: HttpServletResponse): DeferredResult<List<SiteInfo>> {
        val deferred = DeferredResult<List<SiteInfo>>();

        Observable.merge(siteProcessors.map {
            return@map infoComposer.getAndCompose(it, name)
        }).toList().subscribe(object : Observer<List<SiteInfo>> {
            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
                deferred.setErrorResult(e)
            }

            override fun onNext(t: List<SiteInfo>?) {
                deferred.setResult(t)
            }
        })
        return deferred
    }
}