package com.sigmapool.api.kodein

import com.sigmapool.api.coin.ICoinService
import com.sigmapool.api.coin.StubCoinService
import com.sigmapool.api.login.ILoginService
import com.sigmapool.api.login.LoginService
import com.sigmapool.api.miners.IMinerService
import com.sigmapool.api.miners.StubMinerService
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

const val BTC = "btc"
const val LTC = "ltc"

internal val serviceModule = Module("ServiceModule") {

    bind<ICoinService>() with singleton { StubCoinService(instance(BTC)) }
    bind<IMinerService>() with singleton { StubMinerService(instance(BTC)) }
    bind<ILoginService>() with singleton { LoginService(instance(BTC)) }
}