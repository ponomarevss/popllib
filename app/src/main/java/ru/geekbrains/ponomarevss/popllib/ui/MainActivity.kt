package ru.geekbrains.ponomarevss.popllib.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import ru.geekbrains.ponomarevss.popllib.R
import ru.geekbrains.ponomarevss.popllib.mvp.model.Model
import ru.geekbrains.ponomarevss.popllib.mvp.presenter.Presenter
import ru.geekbrains.ponomarevss.popllib.mvp.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    val presenter = Presenter(Model(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnOneListener = View.OnClickListener() {
            presenter.btnOneClick()
        }
        val btnTwoListener = View.OnClickListener {
            presenter.btnTwoClick()
        }
        val btnThreeListener = View.OnClickListener {
            presenter.btnThreeClick()
        }

        btn_counter1.setOnClickListener(btnOneListener)
        btn_counter2.setOnClickListener(btnTwoListener)
        btn_counter3.setOnClickListener(btnThreeListener)
    }

    override fun setButtonOneText(btnText: String) {
        btn_counter1.text = btnText
    }

    override fun setButtonTwoText(btnText: String) {
        btn_counter2.text = btnText
    }

    override fun setButtonThreeText(btnText: String) {
        btn_counter3.text = btnText
    }

}