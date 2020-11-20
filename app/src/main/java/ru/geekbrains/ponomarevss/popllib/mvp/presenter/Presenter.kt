package ru.geekbrains.ponomarevss.popllib.mvp.presenter

import ru.geekbrains.ponomarevss.popllib.mvp.model.Model
import ru.geekbrains.ponomarevss.popllib.mvp.view.MainView

class Presenter (val model: Model, val mainView: MainView) {

    fun btnOneClick() {
        val nextValue = model.next(0)
        mainView.setButtonOneText(nextValue.toString())
    }

    fun btnTwoClick() {
        val nextValue = model.next(1)
        mainView.setButtonTwoText(nextValue.toString())
    }

    fun btnThreeClick() {
        val nextValue = model.next(2)
        mainView.setButtonThreeText(nextValue.toString())
    }
}