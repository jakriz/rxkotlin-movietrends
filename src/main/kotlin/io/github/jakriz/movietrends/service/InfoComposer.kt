package io.github.jakriz.movietrends.service

import io.github.jakriz.movietrends.model.Role
import io.github.jakriz.movietrends.model.SiteInfo
import io.github.jakriz.movietrends.service.processor.SiteProcessor
import rx.Observable

interface InfoComposer {

    fun getAndCompose(siteProcessor: SiteProcessor, name: String, allowedRoles: Set<Role>?): Observable<SiteInfo>
}