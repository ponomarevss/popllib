package ru.geekbrains.ponomarevss.popllib.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.ponomarevss.popllib.R
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.GithubUser
import ru.geekbrains.ponomarevss.popllib.mvp.presenter.UserPresenter
import ru.geekbrains.ponomarevss.popllib.mvp.view.UserView
import ru.geekbrains.ponomarevss.popllib.ui.App
import ru.geekbrains.ponomarevss.popllib.ui.BackButtonListener

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    companion object {
        private const val USER = "user"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                this.putParcelable(USER, user)
            }
        }
    }

    val presenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER)
        UserPresenter(App.instance.router, user)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_user, null)

    override fun init() {
        tv_login.text = presenter.user?.login
    }

    override fun backPressed() = presenter.backClick()

}