package io.github.jakriz.movietrends.service.client

import rx.Observable

interface HttpClient {

    fun get(url: String): Observable<String>
}
