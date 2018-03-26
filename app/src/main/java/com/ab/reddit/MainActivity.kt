package com.ab.reddit

import android.os.Bundle
import android.support.design.widget.Snackbar
import com.toppr.dubbio.v3.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<RedditViewModel>() {
    /**
     * Override for set view model
     *
     * @return view model instance
     */
    override fun getViewModel(): RedditViewModel {
        return model
    }

    @Inject
    lateinit var model: RedditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
}
