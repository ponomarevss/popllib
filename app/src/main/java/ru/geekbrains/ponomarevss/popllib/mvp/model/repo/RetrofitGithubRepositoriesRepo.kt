package ru.geekbrains.ponomarevss.popllib.mvp.model.repo

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.ponomarevss.popllib.mvp.model.api.IDataSource

class RetrofitGithubRepositoriesRepo(val api: IDataSource) : IGithubRepositoriesRepo {

    override fun getRepos(url: String) = api.getRepos(url).subscribeOn(Schedulers.io())


}