package ru.geekbrains.ponomarevss.popllib.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.ponomarevss.popllib.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.popllib.mvp.model.cache.ICache
import ru.geekbrains.ponomarevss.popllib.mvp.model.network.INetworkStatus

class RetrofitGithubUsersRepo(val api: IDataSource, val networkStatus: INetworkStatus, val cache: ICache) : IGithubUsersRepo {

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                cache.putUsers(users).andThen(Single.just(users))
            }
        } else {
            cache.getUsers()
        }
    }.subscribeOn(Schedulers.io())
}