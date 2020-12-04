package ru.geekbrains.ponomarevss.popllib.mvp.view.list

interface RepositoryItemView: IItemView {
    fun setName(text: String)
    fun setForksCount(text: String)
}