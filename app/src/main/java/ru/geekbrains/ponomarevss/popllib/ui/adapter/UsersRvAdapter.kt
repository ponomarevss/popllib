package ru.geekbrains.ponomarevss.popllib.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.*
import kotlinx.android.synthetic.main.item_user.view.*
import ru.geekbrains.ponomarevss.popllib.R
import ru.geekbrains.ponomarevss.popllib.mvp.model.image.IImageLoader
import ru.geekbrains.ponomarevss.popllib.mvp.presenter.list.IUsersListPresenter
import ru.geekbrains.ponomarevss.popllib.mvp.view.list.UserItemView

class UsersRvAdapter(val presenter: IUsersListPresenter, val imageLoader: IImageLoader<ImageView>) : RecyclerView.Adapter<UsersRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)).apply {
            containerView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), UserItemView, LayoutContainer {
        override var pos = -1

        override fun setLogin(text: String) {
            tv_login.text = text
        }

        override fun loadImage(url: String) {
            imageLoader.loadInto(url, iv_avatar)
        }
    }
}