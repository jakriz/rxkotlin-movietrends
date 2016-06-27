package io.github.jakriz.movietrends.service.client

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.rx.rx_string
import org.springframework.stereotype.Service
import rx.Observable

@Service
class FuelHttpClient : HttpClient {

    override fun get(url: String): Observable<String> {
        return Fuel.get(url).rx_string()
    }

}