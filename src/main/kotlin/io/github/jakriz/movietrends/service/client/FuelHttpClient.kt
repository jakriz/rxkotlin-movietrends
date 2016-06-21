package io.github.jakriz.movietrends.service.client

import com.github.kittinunf.fuel.Fuel
import org.springframework.stereotype.Service
import rx.Observable

@Service
class FuelHttpClient : HttpClient {

    override fun get(url: String): Observable<String> {
        val (request, response, result) = Fuel.get(url).response()
        return if (response.httpStatusCode == 200) String(response.data) else null
    }

}