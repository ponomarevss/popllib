package ru.geekbrains.ponomarevss.popllib.mvp.presenter

import moxy.MvpPresenter
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.GithubUser
import ru.geekbrains.ponomarevss.popllib.mvp.model.repo.GithubUsersRepo
import ru.geekbrains.ponomarevss.popllib.mvp.presenter.list.IUsersListPresenter
import ru.geekbrains.ponomarevss.popllib.mvp.view.UsersView
import ru.geekbrains.ponomarevss.popllib.mvp.view.list.UserItemView
import ru.geekbrains.ponomarevss.popllib.navigation.Screens
import ru.terrakok.cicerone.Router

class UsersPresenter(val router: Router, val usersRepo: GithubUsersRepo) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUsersListPresenter {
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        val users = mutableListOf<GithubUser>()

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size

    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { view ->
            router.navigateTo(Screens.UserScreen(this.usersListPresenter.users[view.pos]))
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.clear()
        usersListPresenter.users.addAll(users)
        viewState.updateUsersList()
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

}