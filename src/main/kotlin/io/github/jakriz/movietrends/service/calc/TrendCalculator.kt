package io.github.jakriz.movietrends.service.calc

import io.github.jakriz.movietrends.model.TrendInfo

interface TrendCalculator {

    fun calculate(data: List<Pair<Int, Double>>): TrendInfo?
}