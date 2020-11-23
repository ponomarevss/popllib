package ru.geekbrains.ponomarevss.popllib.mvp.presenter

import moxy.MvpPresenter
import ru.geekbrains.ponomarevss.popllib.mvp.view.MainView
import ru.geekbrains.ponomarevss.popllib.navigation.Screens
import ru.terrakok.cicerone.Router

class MainPresenter(val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClick() {
        router.exit()
    }
}