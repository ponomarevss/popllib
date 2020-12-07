package ru.geekbrains.ponomarevss.popllib.ui.network

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.geekbrains.ponomarevss.popllib.mvp.model.network.INetworkStatus

class AndroidNetworkStatus(context: Context): INetworkStatus {

    private val statusSubject: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    init {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    override fun isOnline(): Observable<Boolean> {
        TODO("Not yet implemented")
    }

    override fun isOnlineSingle(): Single<Boolean> {
        TODO("Not yet implemented")
    }

}