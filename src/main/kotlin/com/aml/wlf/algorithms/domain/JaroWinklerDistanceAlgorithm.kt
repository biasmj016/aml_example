package com.aml.wlf.algorithms.domain

object JaroWinklerDistanceAlgorithm : SimilarityAlgorithm {

    override fun calculateSimilarity(base: String, comparison: String): Double {
        val jaroDistance = jaroDistance(base, comparison)
        val prefixLength = commonPrefix(base, comparison)
        val jaroWinklerDistance = jaroDistance + (0.1 * prefixLength * (1 - jaroDistance))
        return jaroWinklerDistance * 100
    }

    private fun jaroDistance(base: String, comparison: String): Double {
        if (base.isBlank() || comparison.isBlank()) return 0.0
        if (base == comparison) return 1.0

        val matchDistance = (maxOf(base.length, comparison.length) / 2) - 1
        val baseMatches = BooleanArray(base.length)
        val comparisonMatches = BooleanArray(comparison.length)

        val matches = findMatches(base, comparison, matchDistance, baseMatches, comparisonMatches)
        if (matches == 0) return 0.0

        val transpositions = countTranspositions(base, comparison, baseMatches, comparisonMatches)

        return (matches / base.length.toDouble() +
                matches / comparison.length.toDouble() +
                (matches - transpositions / 2.0) / matches) / 3.0
    }

    private fun findMatches(
        base: String,
        comparison: String,
        matchDistance: Int,
        baseMatches: BooleanArray,
        comparisonMatches: BooleanArray
    ): Int {
        return base.indices.asSequence()
            .map { i ->
                val start = maxOf(0, i - matchDistance)
                val end = minOf(i + matchDistance + 1, comparison.length)
                (start until end).firstOrNull { j ->
                    !comparisonMatches[j] && base[i] == comparison[j]
                }?.let { j ->
                    baseMatches[i] = true
                    comparisonMatches[j] = true
                    1
                } ?: 0
            }.sum()
    }

    private fun countTranspositions(
        base: String,
        comparison: String,
        baseMatches: BooleanArray,
        comparisonMatches: BooleanArray
    ): Int {
        var transpositions = 0
        var k = 0
        base.indices.asSequence()
            .filter { baseMatches[it] }
            .forEach { i ->
                while (!comparisonMatches[k]) k++
                if (base[i] != comparison[k]) transpositions++
                k++
            }
        return transpositions
    }

    private fun commonPrefix(base: String, comparison: String, maxPrefixLength: Int = 4): Int {
        return (0 until minOf(base.length, comparison.length, maxPrefixLength))
            .takeWhile { base[it] == comparison[it] }
            .count()
    }
}