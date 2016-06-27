package io.github.jakriz.movietrends.service.processor.rotten.util

fun ratingFromPercentageText(text: String?): Double? {
    text?.let {
        return """(\d+)%""".toRegex().matchEntire(text)?.groups?.get(1)?.value?.toDouble()
    } ?: return null
}
