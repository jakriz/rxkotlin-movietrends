package io.github.jakriz.movietrends.service.calc

import io.github.jakriz.movietrends.model.TrendInfo
import org.apache.commons.math3.stat.regression.SimpleRegression
import org.springframework.stereotype.Service

@Service
class LinearRegressionTrendCalculator : TrendCalculator {

    override fun calculate(data: List<Pair<Int, Double>>): TrendInfo? {
        val regression = SimpleRegression()
        data.forEach { regression.addData(it.first.toDouble(), it.second) }

        val from = data.map { it.first }.min()
        val to = data.map { it.first }.max()

        if (from != null && to != null) {
            val fromPrediction = regression.predict(from.toDouble())
            val toPrediction = regression.predict(to.toDouble())

            return TrendInfo(Pair(from, fromPrediction), Pair(to, toPrediction))
        } else {
            return null
        }
    }
}