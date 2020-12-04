package ru.geekbrains.ponomarevss.popllib.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.GithubUser

interface IGithubUsersRepo {
    fun getUsers() : Single<List<GithubUser>>
}