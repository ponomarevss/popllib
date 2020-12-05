package ru.geekbrains.ponomarevss.popllib.mvp.view.list

interface UserItemView: IItemView {
    fun setLogin(text: String)
    fun loadImage(url: String)
}