package ru.geekbrains.ponomarevss.popllib.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.GithubRepository
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.GithubUser

interface ICache {
    fun putUsers(users: List<GithubUser>) : Completable
    fun getUsers() : Single<List<GithubUser>>
    fun putRepositories(user: GithubUser, repositories: List<GithubRepository>) : Completable
    fun getRepositories(user: GithubUser) : Single<List<GithubRepository>>
}