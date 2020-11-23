package ru.geekbrains.ponomarevss.popllib.mvp.presenter.list

import ru.geekbrains.ponomarevss.popllib.mvp.view.list.IItemView

interface IListPresenter<V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}