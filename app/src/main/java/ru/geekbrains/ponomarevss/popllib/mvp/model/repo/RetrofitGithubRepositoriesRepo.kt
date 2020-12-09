package ru.geekbrains.ponomarevss.popllib.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.ponomarevss.popllib.mvp.model.api.IDataSource
import ru.geekbrains.ponomarevss.popllib.mvp.model.cache.ICache
import ru.geekbrains.ponomarevss.popllib.mvp.model.cache.RoomCache
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.GithubRepository
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.GithubUser
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.room.RoomGithubRepository
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.room.RoomGithubUser
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.room.db.Database
import ru.geekbrains.ponomarevss.popllib.mvp.model.network.INetworkStatus
import java.lang.RuntimeException

class RetrofitGithubRepositoriesRepo(val api: IDataSource, val networkStatus: INetworkStatus, val cache: ICache) : IGithubRepositoriesRepo {

    override fun getRepositories(user: GithubUser): Single<List<GithubRepository>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            user.reposUrl?.let {url ->
                api.getRepos(url).flatMap { repositories ->
                    cache.putRepositories(user, repositories).andThen(Single.just(repositories))
                }
            } ?: Single.error<List<GithubRepository>>(RuntimeException("user has no repositories url")).subscribeOn(Schedulers.io())

        } else {
            cache.getRepositories(user)
        }
    }.subscribeOn(Schedulers.io())
}