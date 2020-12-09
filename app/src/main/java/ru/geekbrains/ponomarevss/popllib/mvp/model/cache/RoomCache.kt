package ru.geekbrains.ponomarevss.popllib.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.GithubRepository
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.GithubUser
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.room.RoomGithubRepository
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.room.RoomGithubUser
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.room.db.Database
import java.lang.RuntimeException

class RoomCache (val db: Database?): ICache {

    override fun putUsers(users: List<GithubUser>): Completable = Completable.create { emitter ->
        db?.let {
            val roomUsers = users.map { user ->
                RoomGithubUser(user.id ?: "", user.login ?: "", user.avatarUrl ?: "", user.reposUrl ?: "")
            }
            it.userDao.insert(roomUsers)
            emitter.onComplete() }
            ?: emitter.onError(RuntimeException("Database not found"))
    }

    override fun getUsers(): Single<List<GithubUser>> = Single.fromCallable {
        db?.let {
            it.userDao.getAll().map { user ->
                GithubUser(user.id, user.login, user.avatarUrl, user.reposUrl)
            }
        }
    }

    override fun putRepositories(user: GithubUser, repositories: List<GithubRepository>): Completable = Completable.create { emitter ->
        db?.let { database ->
            val roomUser = user.login?.let { database.userDao.findByLogin(it) } ?: throw RuntimeException("No such user in database")
            val roomRepos = repositories.map { repository ->
                RoomGithubRepository(
                    repository.id ?: "",
                    repository.name ?: "",
                    repository.forksCount ?: "",
                    repository.fullName ?: "",
                    roomUser.id
                )
            }
            database.repositoryDao.insert(roomRepos)
            emitter.onComplete()
        }
            ?: emitter.onError(RuntimeException("Database not found"))
    }

    override fun getRepositories(user: GithubUser): Single<List<GithubRepository>> = Single.fromCallable {
        db?.let { database ->
            val roomUser = user.login?.let { database.userDao.findByLogin(it) } ?: throw java.lang.RuntimeException("No such user in database")
            database.repositoryDao.findForUser(roomUser.id).map { repository ->
                GithubRepository(repository.id, repository.name, repository.forksCount, repository.fullName)
            }
        }
    }
}