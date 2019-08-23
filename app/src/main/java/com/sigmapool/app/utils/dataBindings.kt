package com.sigmapool.app.utils

import android.widget.ImageView
import android.widget.RadioButton
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.sigmapool.app.screens.bottomSheetScreen.ViewPagerAdapter
import com.sigmapool.app.screens.home.adapter.CoinViewPagerAdapter
import com.sigmapool.app.screens.home.coin.CoinsVM
import com.sigmapool.app.utils.slider.MainSliderAdapter
import com.sigmapool.common.models.BlogDto
import ss.com.bannerslider.Slider
import ss.com.bannerslider.event.OnSlideClickListener


@BindingAdapter("onCheckedInitValue")
fun radioBtnOnClick(view: RadioButton, value: Boolean) {
    var isChecked = value
    view.setOnClickListener {
        isChecked = !isChecked
        view.isChecked = isChecked
    }
}

@BindingAdapter("app:onNavigationItemSelected")
fun onNavigationItemSelected(
    view: BottomNavigationView,
    listener: BottomNavigationView.OnNavigationItemSelectedListener
) {
    view.setOnNavigationItemSelectedListener(listener)
}

@BindingAdapter("app:initCoinAdapter", "app:fragmentManager", requireAll = true)
fun initCoinAdapter(view: ViewPager, vm: CoinsVM, fragmentManager: FragmentManager) {
    view.adapter = CoinViewPagerAdapter(vm.getCoins(), fragmentManager)
}

@BindingAdapter("app:viewPagerAdapter")
fun viewPagerAdapter(view: FragmentViewPager, fragmentManager: FragmentManager) {
    view.adapter = ViewPagerAdapter(fragmentManager)
}

@BindingAdapter("app:onScreenChange")
fun onScreenChange(view: FragmentViewPager, position: Int) {
    view.currentItem = position
}

@BindingAdapter("app:src")
fun imageMipmapSrc(view: ImageView, mipmapRes: Int) {
    view.setImageResource(mipmapRes)
}

@BindingAdapter("bind:pager")
fun bindViewPagerTabs(view: TabLayout, pagerView: ViewPager) {
    view.setupWithViewPager(pagerView, true)
}

@BindingAdapter("app:setImages", "app:onImageClick", requireAll = true)
fun setImages(slider: Slider, items: ArrayList<BlogDto>?, listener: OnSlideClickListener?) {
    items.let {
        val adapter = it?.let { it1 -> MainSliderAdapter(it1) }
        slider.setAdapter(adapter)
        slider.setOnSlideClickListener(listener)
    }
}
