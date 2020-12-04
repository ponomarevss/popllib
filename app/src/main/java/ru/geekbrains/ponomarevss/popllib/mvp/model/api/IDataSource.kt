package ru.geekbrains.ponomarevss.popllib.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.GithubRepository
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.GithubUser

interface IDataSource {
    @GET("users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getRepos(@Url url: String): Single<List<GithubRepository>>
}