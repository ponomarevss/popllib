package ru.geekbrains.ponomarevss.popllib.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}