package ru.geekbrains.ponomarevss.popllib.mvp.model.repo

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

    fun getUsers() : List<GithubUser> {
        return repositories
    }
}