package ru.geekbrains.ponomarevss.popllib.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.room.MIGRATION_1_2
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.room.RoomGithubRepository
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.room.RoomGithubUser
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.room.dao.GithubRepositoryDao
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.room.dao.GithubUserDao
import java.lang.RuntimeException

@androidx.room.Database(entities = [RoomGithubUser::class, RoomGithubRepository::class], version = 2)
abstract class Database : RoomDatabase() {
    abstract val userDao: GithubUserDao
    abstract val repositoryDao: GithubRepositoryDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance ?: throw RuntimeException("Database has not been created")
        fun create(context: Context) {
            instance ?: let {
                instance = Room.databaseBuilder(context, Database::class.java, DB_NAME)
                    .addMigrations(MIGRATION_1_2)
                    .build()
            }
        }
    }
}