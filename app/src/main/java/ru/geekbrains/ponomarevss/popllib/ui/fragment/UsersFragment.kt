package ru.geekbrains.ponomarevss.popllib.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_users.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.ponomarevss.popllib.R
import ru.geekbrains.ponomarevss.popllib.mvp.model.repo.GithubUsersRepo
import ru.geekbrains.ponomarevss.popllib.mvp.presenter.UsersPresenter
import ru.geekbrains.ponomarevss.popllib.mvp.view.UsersView
import ru.geekbrains.ponomarevss.popllib.ui.App
import ru.geekbrains.ponomarevss.popllib.ui.BackButtonListener
import ru.geekbrains.ponomarevss.popllib.ui.adapter.UsersRvAdapter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter by moxyPresenter {
        UsersPresenter(App.instance.router, GithubUsersRepo())
    }

    val adapter by lazy {
        UsersRvAdapter(presenter.usersListPresenter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_users, null)

    override fun init() {
        rv_users.layoutManager = LinearLayoutManager(requireContext())
        rv_users.adapter = adapter
    }

    override fun updateUsersList() {
        adapter.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()

}