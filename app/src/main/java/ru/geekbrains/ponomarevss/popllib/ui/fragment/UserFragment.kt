package ru.geekbrains.ponomarevss.popllib.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.android.synthetic.main.fragment_user.iv_avatar
import kotlinx.android.synthetic.main.fragment_user.tv_login
import kotlinx.android.synthetic.main.item_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.ponomarevss.popllib.R
import ru.geekbrains.ponomarevss.popllib.mvp.model.api.ApiHolder
import ru.geekbrains.ponomarevss.popllib.mvp.model.cache.RoomCache
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.GithubUser
import ru.geekbrains.ponomarevss.popllib.mvp.model.entity.room.db.Database
import ru.geekbrains.ponomarevss.popllib.mvp.model.image.IImageLoader
import ru.geekbrains.ponomarevss.popllib.mvp.model.repo.RetrofitGithubRepositoriesRepo
import ru.geekbrains.ponomarevss.popllib.mvp.presenter.UserPresenter
import ru.geekbrains.ponomarevss.popllib.mvp.view.UserView
import ru.geekbrains.ponomarevss.popllib.ui.App
import ru.geekbrains.ponomarevss.popllib.ui.BackButtonListener
import ru.geekbrains.ponomarevss.popllib.ui.adapter.RepositoriesRvAdapter
import ru.geekbrains.ponomarevss.popllib.ui.image.GlideImageLoader
import ru.geekbrains.ponomarevss.popllib.ui.network.AndroidNetworkStatus

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
        val user = arguments?.getParcelable<GithubUser>(USER) as GithubUser
        UserPresenter(
            AndroidSchedulers.mainThread(),
            App.instance.router,
            user,
            RetrofitGithubRepositoriesRepo(ApiHolder.api, AndroidNetworkStatus(requireContext()), RoomCache(Database.getInstance()))
        )
    }

    val adapter by lazy {
        RepositoriesRvAdapter(presenter.repositoriesListPresenter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_user, null)

    override fun init() {
        rv_repositories.layoutManager = LinearLayoutManager(requireContext())
        rv_repositories.adapter = adapter
    }

    override fun updateRepositoriesList() {
        adapter.notifyDataSetChanged()
    }

    override fun setLogin(text: String) {
        tv_login.text = text
    }

    override fun loadImage(url: String) {
        val imageLoader: IImageLoader<ImageView> = GlideImageLoader()
        imageLoader.loadInto(url, iv_avatar)
    }

    override fun backPressed() = presenter.backClick()

}
