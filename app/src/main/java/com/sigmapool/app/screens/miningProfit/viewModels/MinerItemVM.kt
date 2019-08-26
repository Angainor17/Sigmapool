package com.sigmapool.app.screens.miningProfit.viewModels

import android.graphics.Color
import com.sigmapool.app.R
import com.sigmapool.app.provider.currency.ICurrencyProvider
import com.sigmapool.app.utils.*
import com.sigmapool.common.listLibrary.viewmodel.BaseItemViewModel
import com.sigmapool.common.models.MinerDto
import com.sigmapool.common.utils.FLOAT_PATTERN
import com.sigmapool.common.utils.INT_PATTERN
import com.sigmapool.common.utils.format
import kotlin.math.abs

class MinerItemVM(
    private val currencyProvider: ICurrencyProvider,
    private val miner: MinerDto
) : BaseItemViewModel {

    val name: String = miner.title
    val hashratePower: CharSequence = createHashratePower(miner)

    var btcValue: String = createBtcValueText(0f)

    var revenuePowerCost = ""
    val shutdownPrice: String = getCurrencyLabel() + " " + miner.shutdownPrice
    var profit = ""
    var profitValue = 0f

    init {
        initPowerCost(1f)
    }

    fun initPowerCost(powerCost: Float) {
        profitValue = miner.revenue - powerCost
        profit = (if (profitValue < 0) "-" else "") + getCurrencyLabel() + " " + abs(profitValue).format(FLOAT_PATTERN)
        revenuePowerCost = getCurrencyLabel() + miner.revenue + " / " + getCurrencyLabel() + powerCost
    }

    companion object {
        val itemType = MinerItemVM::class.hashCode()
    }

    private fun getCurrencyLabel(): String = currencyProvider.getSymbol().toString()

    override val itemViewType: Int = itemType

    override fun areItemsTheSame(item: BaseItemViewModel) = this == item

    override fun areContentsTheSame(item: BaseItemViewModel): Boolean {
        return item is MinerItemVM && item.name == this.name
    }

    private fun createBtcValueText(value: Float) =
        getString(R.string.btc_caps) + " - " + getCurrencyLabel() + " " + value.format(INT_PATTERN)

    private fun createHashratePower(miner: MinerDto): CharSequence {
        val hashrate = formatLongValue(miner.hashrate)

        return hashrate.substring(0, hashrate.length - 1).spannable(Color.BLACK) +
                hashrate.substring(hashrate.length - 1).spannable(getColor(R.color.titleGray)) +
                (" / " + miner.power).spannable(Color.BLACK) +
                getString(R.string.power_postfix).spannable(getColor(R.color.titleGray))
    }

    fun initCoin(value: Float) {
        btcValue = createBtcValueText(value)
    }
}

fun String.spannable(color: Int, textSize: Int = 15) = spannableString(
    this,
    textSize,
    color,
    GOOGLE_FONT_FAMILY
)