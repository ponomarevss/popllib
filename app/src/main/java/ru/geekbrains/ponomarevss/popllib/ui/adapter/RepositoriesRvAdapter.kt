package ru.geekbrains.ponomarevss.popllib.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repository.view.*
import kotlinx.android.synthetic.main.item_user.view.*
import ru.geekbrains.ponomarevss.popllib.R
import ru.geekbrains.ponomarevss.popllib.mvp.presenter.list.IRepositoriesListPresenter
import ru.geekbrains.ponomarevss.popllib.mvp.presenter.list.IUsersListPresenter
import ru.geekbrains.ponomarevss.popllib.mvp.view.list.RepositoryItemView
import ru.geekbrains.ponomarevss.popllib.mvp.view.list.UserItemView

class RepositoriesRvAdapter(val presenter: IRepositoriesListPresenter) : RecyclerView.Adapter<RepositoriesRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)).apply {
            containerView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), RepositoryItemView, LayoutContainer {
        override fun setName(text: String) = with(containerView) {
            tv_name.text = text
        }

        override fun setForksCount(text: String) = with(containerView) {
            tv_forks_count.text = text
        }

        override var pos = -1
    }
}