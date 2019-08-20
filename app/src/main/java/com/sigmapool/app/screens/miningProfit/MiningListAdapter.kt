package com.sigmapool.app.screens.miningProfit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sigmapool.app.R
import com.sigmapool.app.screens.miningProfit.viewModels.MinerHeaderVM
import com.sigmapool.common.listLibrary.IItemBindingHelper
import com.sigmapool.common.listLibrary.pagedlist.SimplePagedAdapter


class MiningListAdapter(private val minerHeaderVM: MinerHeaderVM, itemLayoutProvider: IItemBindingHelper) :
    SimplePagedAdapter(itemLayoutProvider) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == MINER_LIST_HEADER) {
            return ViewHolder(
                itemLayoutProvider.getBindingFunction(viewType),
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.seek_bar,
                    parent,
                    false
                )
            )
        }

        return super.onCreateViewHolder(parent, viewType)
    }


    override fun getItemId(position: Int): Long {
        if (position == 0) return -1L

        return position.toLong()
    }

    override fun getItemCount(): Int = super.getItemCount() + 1

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return MINER_LIST_HEADER

        return getItem(position - 1)?.itemViewType ?: super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            holder.bind(minerHeaderVM)
            return
        }
        getItem(position - 1)?.let {
            holder.bind(it)
        }
    }
}