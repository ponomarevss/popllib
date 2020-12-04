package ru.geekbrains.ponomarevss.popllib.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.GithubUser
import ru.geekbrains.ponomarevss.popllib.mvp.model.repo.RetrofitGithubUsersRepo
import ru.geekbrains.ponomarevss.popllib.mvp.presenter.list.IUsersListPresenter
import ru.geekbrains.ponomarevss.popllib.mvp.view.UsersView
import ru.geekbrains.ponomarevss.popllib.mvp.view.list.UserItemView
import ru.geekbrains.ponomarevss.popllib.navigation.Screens
import ru.terrakok.cicerone.Router

class UsersPresenter(
    val mainThreadScheduler: Scheduler,
    val router: Router,
    val retrofitGithubUsersRepo: RetrofitGithubUsersRepo
    ) : MvpPresenter<UsersView>() {

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
            val user = usersListPresenter.users[view.pos]
            router.navigateTo(Screens.UserScreen(user))
        }
    }

    private fun loadData() {
        retrofitGithubUsersRepo.getUsers()
            .observeOn(mainThreadScheduler)
            .subscribe({list ->
            usersListPresenter.users.clear()
            usersListPresenter.users.addAll(list)
            viewState.updateUsersList()
        }, {
            println("Error: ${it.message}")
        })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

}