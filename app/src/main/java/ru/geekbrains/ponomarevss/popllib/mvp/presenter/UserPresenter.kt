package ru.geekbrains.ponomarevss.popllib.mvp.presenter

import moxy.MvpPresenter
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.GithubUser
import ru.geekbrains.ponomarevss.popllib.mvp.view.UserView
import ru.terrakok.cicerone.Router

class UserPresenter(val router: Router, val user: GithubUser) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLogin(user.login)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

}