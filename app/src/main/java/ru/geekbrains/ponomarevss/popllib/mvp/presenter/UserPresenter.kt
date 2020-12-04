package ru.geekbrains.ponomarevss.popllib.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.GithubRepository
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.GithubUser
import ru.geekbrains.ponomarevss.popllib.mvp.model.repo.RetrofitGithubRepositoriesRepo
import ru.geekbrains.ponomarevss.popllib.mvp.presenter.list.IRepositoriesListPresenter
import ru.geekbrains.ponomarevss.popllib.mvp.view.UserView
import ru.geekbrains.ponomarevss.popllib.mvp.view.list.RepositoryItemView
import ru.terrakok.cicerone.Router

class UserPresenter(
    val mainThreadScheduler: Scheduler,
    val router: Router,
    val user: GithubUser,
    val retrofitGithubRepositoriesRepo: RetrofitGithubRepositoriesRepo
    ) : MvpPresenter<UserView>() {

    class RepositoriesListPresenter : IRepositoriesListPresenter {
        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        val repositories = mutableListOf<GithubRepository>()

        override fun bindView(view: RepositoryItemView) {
            val repository = repositories[view.pos]
            view.setName(repository.name)
            view.setForksCount(repository.forksCount.toString())
        }

        override fun getCount() = repositories.size

    }

    val repositoriesListPresenter = RepositoriesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setLogin(user.login)
        loadData()
    }

    private fun loadData() {
        retrofitGithubRepositoriesRepo.getRepos(user.reposUrl)
            .observeOn(mainThreadScheduler)
            .subscribe({list ->
                repositoriesListPresenter.repositories.clear()
                repositoriesListPresenter.repositories.addAll(list)
                viewState.updateRepositoriesList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

}