package ru.geekbrains.ponomarevss.popllib.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.GithubRepository

interface IGithubRepositoriesRepo {
    fun getRepos(url : String) : Single<List<GithubRepository>>
}