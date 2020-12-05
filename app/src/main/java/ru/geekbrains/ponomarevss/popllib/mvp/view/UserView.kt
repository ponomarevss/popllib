package ru.geekbrains.ponomarevss.popllib.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView: MvpView {
    fun init()
    fun updateRepositoriesList()
    fun setLogin(text : String)
    fun loadImage(url: String)
}