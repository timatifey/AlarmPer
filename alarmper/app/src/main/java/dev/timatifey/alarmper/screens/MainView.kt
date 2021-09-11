package dev.timatifey.alarmper.screens

interface MainView {
    fun isBottomBarVisible(isVisible: Boolean)
    fun setBottomBarSelected(itemId: Int)
    fun setViewPagerSelected(fragment: MainFragmentStateAdapter.MainFragments)
}