package com.sigmapool.api.miners

import com.sigmapool.api.models.Miner
import kotlinx.coroutines.delay
import retrofit2.Retrofit
import kotlin.random.Random

internal class StubMinerService(retrofit: Retrofit) : IMinerService {

    private val items = List(120) {
        Miner(
            it.toLong(),
            TitleModel("Antminer S$it Pro", "", ""),
            "btc",
            10000000000000,
            2500 + it,
            20f + it + Random.nextInt(0, 10) + (Random.nextInt(0, 10) / 100),
            15f + (it / 100)
        )
    }

    override suspend fun getMiners(page: Int, perPage: Int): List<Miner> {
        if (page >= items.size) {
            return emptyList()
        }

        delay(2000)

        return items.slice(IntRange(page, page + perPage - 1))
    }
}