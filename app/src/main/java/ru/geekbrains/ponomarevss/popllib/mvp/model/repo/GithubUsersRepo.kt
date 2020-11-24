package ru.geekbrains.ponomarevss.popllib.mvp.model.repo

import io.reactivex.rxjava3.core.Observable
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.GithubUser

class GithubUsersRepo {
    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5"),
        GithubUser("login6")
    )

    fun getUsers(): Observable<GithubUser> = Observable.create {emitter ->
        repositories.forEach{
            emitter.onNext(it)
        }
    }

//    fun getUsers(): Observable<GithubUser> = Observable.fromIterable(repositories)

}